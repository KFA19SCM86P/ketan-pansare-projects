import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
static DBCollection myReviews;
public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
 myReviews= db.getCollection("myReviews");	
return myReviews; 
}


public static String insertReview(String productname,String username,String producttype,String productmaker,String reviewrating,String reviewdate,String reviewtext,String retailerpin,String price,String retailercity,String age,String gender,String occupation)
{
	try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myReviews").
				append("userName", username).
				append("productName", productname).
				append("productType", producttype).
				append("productMaker", productmaker).
				append("reviewRating",Integer.parseInt(reviewrating)).
				append("reviewDate", reviewdate).
				append("reviewText", reviewtext).
				append("retailerpin", retailerpin).
				append("retailercity", retailercity).
				append("price",(int) Double.parseDouble(price)).
				append("age",age).
				append("gender",gender).
				append("occupation",occupation);
			myReviews.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
		return "UnSuccessfull";
		}	
		
}

public static HashMap<String, ArrayList<Review>> selectReview()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("productName")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("productName"), arr);
			}
			ArrayList<Review> listReview = reviews.get(obj.getString("productName"));		
			Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
				obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"));
			//add to review hashmap
			listReview.add(review);
		
			}
 		return reviews;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return reviews;	
		}	

     
	}
	

  public static  ArrayList <Bestrating> topProducts(){
	  ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
	  try{
		  
	  getConnection();
	  int retlimit =5;
	  DBObject sort = new BasicDBObject();
	  sort.put("reviewRating",-1);
	  DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
	  while(cursor.hasNext()) {
		  BasicDBObject obj = (BasicDBObject) cursor.next();
		  		  		   
		  String prodcutnm = obj.get("productName").toString();
		  String rating = obj.get("reviewRating").toString();
	      Bestrating best = new Bestrating(prodcutnm,rating);
		  Bestrate.add(best);
	  }
	
	}catch (Exception e){ System.out.println(e.getMessage());}
   return Bestrate;
  }
  
  	  public static ArrayList <Mostsoldzip> mostsoldZip(){
	  ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
	  try{
		  
	  getConnection();
      DBObject groupProducts = new BasicDBObject("_id","$retailerpin"); 
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  
	  DBObject sortFields = new BasicDBObject("count",-1);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
      for (DBObject res : output.results()) {
        
		String zipcode =(res.get("_id")).toString();
        String count = (res.get("count")).toString();	
        Mostsoldzip mostsldzip = new Mostsoldzip(zipcode,count);
		mostsoldzip.add(mostsldzip);
	
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return mostsoldzip;
  }
  
   public static ArrayList <Mostsold> mostsoldProducts(){
	  ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
	  try{
		  
	  
      getConnection();
      DBObject groupProducts = new BasicDBObject("_id","$productName"); 
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  
	  DBObject sortFields = new BasicDBObject("count",-1);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
	  
      for (DBObject res : output.results()) {
	  
      
       
		String prodcutname =(res.get("_id")).toString();
        String count = (res.get("count")).toString();	
        Mostsold mostsld = new Mostsold(prodcutname,count);
		mostsold.add(mostsld);
	
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return mostsold;
  }	

  //Get all the reviews grouped by product and zip code;
public static ArrayList<Review> selectReviewForChart() {

		
        ArrayList<Review> reviewList = new ArrayList<Review>();
        try {

            getConnection();
            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
            dbObjIdMap.put("retailerpin", "$retailerpin");
            dbObjIdMap.put("productName", "$productName");
            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
            groupFields.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields);

            DBObject projectFields = new BasicDBObject("_id", 0);
            projectFields.put("retailerpin", "$_id");
            projectFields.put("productName", "$productName");
            projectFields.put("reviewCount", "$count");
            DBObject project = new BasicDBObject("$project", projectFields);

            DBObject sort = new BasicDBObject();
            sort.put("reviewCount", -1);

            DBObject orderby = new BasicDBObject();            
            orderby = new BasicDBObject("$sort",sort);
            

            AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

            for (DBObject result : aggregate.results()) {

                BasicDBObject obj = (BasicDBObject) result;
                Object o = com.mongodb.util.JSON.parse(obj.getString("retailerpin"));
                BasicDBObject dbObj = (BasicDBObject) o;
                Review review = new Review(dbObj.getString("productName"), dbObj.getString("retailerpin"),
                        obj.getString("reviewCount"), null);
                reviewList.add(review);
                
            }
            return reviewList;

        }

        catch (

        Exception e) {
            reviewList = null;
            
            return reviewList;
        }

    }
	
public static ArrayList<Review> selectAllReview()
{	
	ArrayList<Review> reviews=null;
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new ArrayList<>();
	while (cursor.hasNext())
	{		
			BasicDBObject obj = (BasicDBObject) cursor.next();
			Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
				obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"));
			
			reviews.add(review);
		
			}
 		return reviews;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return reviews;	
		}	

     
}

public static HashMap<String,ArrayList<Review>> selectTop5ProdsByCity(int n,int rating,boolean rating_req)
{	
	HashMap<String,ArrayList<Review>> map = new HashMap<>();
	
	try
		{

		getConnection();
		
		//DBCollection coll = db.getCollection("myReviews");
		//call distinct method and store the result in list l1
		List cities= myReviews.distinct("retailercity");
		
		 for(int i=0;i<cities.size();i++){
			 
			ArrayList<Review> reviews=new ArrayList<>();;
			BasicDBObject sort = new BasicDBObject();
			sort.put("price",-1);
			BasicDBObject obj1 = new BasicDBObject();
			obj1.put("retailercity",cities.get(i));
			if(rating_req)
			obj1.put("reviewRating", rating);
			DBCursor cursorin = myReviews.find(obj1).limit(n).sort(sort);
			while(cursorin.hasNext()){
				 
				
				BasicDBObject obj = (BasicDBObject) cursorin.next();
				Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
					obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"));
				
				reviews.add(review);
			}
			
			map.put(cities.get(i).toString(),reviews);
		}
	
		
 		return map;
		}
	catch(Exception e)
		{
		 return map;	
		}	

     
}

public static HashMap<String,ArrayList<Review>> selectTop5ProdsByZip(int n,int rating,boolean rating_req)
{	
	HashMap<String,ArrayList<Review>> map = new HashMap<>();
	
	try
		{

		getConnection();
		
		//DBCollection coll = db.getCollection("myReviews");
		//call distinct method and store the result in list l1
		List zips= myReviews.distinct("retailerpin");
		
		
		 for(int i=0;i<zips.size();i++){
			 
			ArrayList<Review> reviews=new ArrayList<>();;
			BasicDBObject sort = new BasicDBObject();
			sort.put("price",-1);
			BasicDBObject obj1 = new BasicDBObject();
			obj1.put("retailerpin",zips.get(i));
			if(rating_req)
			obj1.put("reviewRating", rating);
			DBCursor cursorin = myReviews.find(obj1).limit(n).sort(sort);
			while(cursorin.hasNext()){
				 
				
				BasicDBObject obj = (BasicDBObject) cursorin.next();
				Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
					obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"));
				
				reviews.add(review);
			}
			
			map.put(zips.get(i).toString(),reviews);
		}
	
		
 		return map;
		}
	catch(Exception e)
		{
		 return map;	
		}	     
}


public static HashMap<String,ArrayList<Review>> selectTop5ProdsByBrand(int n,int rating)
{	
	HashMap<String,ArrayList<Review>> map = new HashMap<>();
	
	try
		{

		getConnection();
		
		//DBCollection coll = db.getCollection("myReviews");
		//call distinct method and store the result in list l1
		List brands= myReviews.distinct("productMaker");
		
		
		 for(int i=0;i<brands.size();i++){
			 
			ArrayList<Review> reviews=new ArrayList<>();;
			 
			BasicDBObject obj1 = new BasicDBObject();
			obj1.put("productMaker",brands.get(i));
			obj1.put("reviewRating", rating);
			DBCursor cursorin = myReviews.find(obj1).limit(n);
			while(cursorin.hasNext()){
				 
				
				BasicDBObject obj = (BasicDBObject) cursorin.next();
				Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
					obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"));
				
				reviews.add(review);
			}
			
			map.put(brands.get(i).toString(),reviews);
		}
	
		
 		return map;
		}
	catch(Exception e)
		{
		 return map;	
		}	     
}

public static ArrayList<Review> selectCommentPattern(String text)
{	
	ArrayList<Review> reviews=new ArrayList<>();;
	
	try
		{

		getConnection();
	
		BasicDBObject pattern = new BasicDBObject("$regex","."+text+".*");
		BasicDBObject obj1 = new BasicDBObject();
		obj1.put("reviewText",pattern);
		DBCursor cursorin = myReviews.find(obj1);
		while(cursorin.hasNext()){
			 
			
			BasicDBObject obj = (BasicDBObject) cursorin.next();
			Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("productMaker"),
				obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("retailerpin"),obj.getString("price"),obj.getString("retailercity"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"));
			
			reviews.add(review);
		}
		
	

		
 		return reviews;
		}
	catch(Exception e)
		{
		 return reviews;	
		}	     
}

public static HashMap<String,Integer> getMedian()
{	
	
	HashMap<String, Integer> mapObj = new HashMap<String, Integer>();
	
	try
		{

	getConnection();
	List city = myReviews.distinct("retailercity");
	for(int i=0; i<city.size();i++){
		System.out.println(city.get(i));
		BasicDBObject query = new BasicDBObject();
		query.put("retailercity",city.get(i));
			double docsCount = (double)myReviews.count(query);
		if(docsCount ==0){
				mapObj.put(city.get(i).toString(), 0);
				continue;
		}
		
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("price",1);
		DBCursor cursor = null;
		int limitSize = (docsCount % 2 ==0) ? 2: 1;
		
		int skipDocs = (int) Math.ceil(docsCount/2)-1;
		
		cursor = myReviews.find(query).sort(sort).skip(skipDocs).limit(limitSize);
		
		int cnt = 0;
		int totalPrice = 0;
		while(cursor.hasNext()){
			cnt++;
			BasicDBObject dataObj = (BasicDBObject) cursor.next(); 
			totalPrice += dataObj.getInt("price");
		}
		int medianPrice = totalPrice / cnt;
		mapObj.put(city.get(i).toString(), medianPrice);
		
	}
		}
		catch(Exception e)
		{
		 	
		}	

     return mapObj;
	}





  

	
}	