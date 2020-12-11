import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
import pojo.*;

public class MongoDBDataStoreUtilities {
    static DBCollection myReviews;
    public static DBCollection getConnection() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("homehubmongo");
        myReviews = db.getCollection("myReviews");
        return myReviews;
    }


    public static String insertReview(Review review) {
        try {
            getConnection();
            BasicDBObject doc = new BasicDBObject("title", "myReviews").
            append("appointmentId", review.getAppointmentId()).
            append("userId", review.getUserId()).
            append("category", review.getCategory()).
            append("professionalId", review.getProfessionalId()).
            append("serviceId", review.getServiceId()).
            append("city", review.getCity()).
            append("totalCharges", (int) review.getTotalCharges()).
            append("reviewRating", review.getReviewRating()).
            append("reviewText", review.getReviewText()).
            append("reviewDate", review.getReviewDate());

            myReviews.insert(doc);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }

    public static HashMap < String, ArrayList < Review >> getReviews() {
        HashMap < String, ArrayList < Review >> reviews = null;

        try {

            getConnection();
            DBCursor cursor = myReviews.find();
            reviews = new HashMap < String, ArrayList < Review >> ();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("professionalId"))) {
                    ArrayList < Review > arr = new ArrayList < Review > ();
                    reviews.put(obj.getString("professionalId"), arr);
                }
                ArrayList < Review > listReview = reviews.get(obj.getString("professionalId"));
                Review review = new Review(obj.getInt("appointmentId"), obj.getString("userId"), obj.getString("category"), obj.getString("professionalId"),
                    obj.getString("serviceId"), obj.getString("city"), obj.getDouble("totalCharges"),
                    obj.getInt("reviewRating"), obj.getString("reviewText"), obj.getString("reviewDate"));
                //add to review hashmap
                listReview.add(review);
            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }
    }

    public static ArrayList <BestProfessional> topRatedProfessionals() {
        ArrayList < BestProfessional > bestProfessionals = new ArrayList < BestProfessional > ();
        try {
            getConnection();
            int retlimit = 5;
            DBObject sort = new BasicDBObject();
            sort.put("reviewRating", -1);
            DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();
                String prodcutnm = obj.get("professionalId").toString();
                String rating = obj.get("reviewRating").toString();
                BestProfessional best = new BestProfessional(prodcutnm, rating);
                bestProfessionals.add(best);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bestProfessionals;
    }

    public static ArrayList < BestService > topRatedServices() {
        ArrayList < BestService > bestServices = new ArrayList < BestService > ();
        try {
            getConnection();
            int retlimit = 5;
            DBObject sort = new BasicDBObject();
            sort.put("reviewRating", -1);
            DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();
                String prodcutnm = obj.get("serviceId").toString();
                String rating = obj.get("reviewRating").toString();
                BestService best = new BestService(prodcutnm, rating);
                bestServices.add(best);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bestServices;
    }

    // public static ArrayList < Mostsoldzip > mostsoldZip() {
    //     ArrayList < Mostsoldzip > mostsoldzip = new ArrayList < Mostsoldzip > ();
    //     try {
    //         getConnection();
    //         DBObject groupProducts = new BasicDBObject("_id", "$storeZip");
    //         groupProducts.put("count", new BasicDBObject("$sum", 1));
    //         DBObject group = new BasicDBObject("$group", groupProducts);
    //         DBObject limit = new BasicDBObject();
    //         limit = new BasicDBObject("$limit", 5);

    //         DBObject sortFields = new BasicDBObject("count", -1);
    //         DBObject sort = new BasicDBObject("$sort", sortFields);
    //         AggregationOutput output = myReviews.aggregate(group, sort, limit);
    //         for (DBObject res: output.results()) {
    //             String zipcode = (res.get("_id")).toString();
    //             String count = (res.get("count")).toString();
    //             Mostsoldzip mostsldzip = new Mostsoldzip(zipcode, count);
    //             mostsoldzip.add(mostsldzip);
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return mostsoldzip;
    // }

    //    public static ArrayList <Mostsold> mostsoldProducts(){
    // 	  ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
    // 	  try{
    //       getConnection();
    //       DBObject groupProducts = new BasicDBObject("_id","$productName"); 
    // 	  groupProducts.put("count",new BasicDBObject("$sum",1));
    // 	  DBObject group = new BasicDBObject("$group",groupProducts);
    // 	  DBObject limit=new BasicDBObject();
    //       limit=new BasicDBObject("$limit",5);

    // 	  DBObject sortFields = new BasicDBObject("count",-1);
    // 	  DBObject sort = new BasicDBObject("$sort",sortFields);
    // 	  AggregationOutput output = myReviews.aggregate(group,sort,limit);

    //       for (DBObject res : output.results()) {



    // 		String prodcutname =(res.get("_id")).toString();
    //         String count = (res.get("count")).toString();	
    //         Mostsold mostsld = new Mostsold(prodcutname,count);
    // 		mostsold.add(mostsld);

    // 	  }
    // 	}catch (Exception e){ System.out.println(e.getMessage());}
    //       return mostsold;
    //   }	

    // public static ArrayList<Review> selectReviewForChart() {

    // 	System.out.println("Select review for chart method called!");


    //         ArrayList<Review> reviewList = new ArrayList<Review>();
    //         try {

    //             getConnection();
    //             Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
    //             dbObjIdMap.put("storeZip", "$storeZip");
    //             dbObjIdMap.put("productName", "$productName");
    //             DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
    //             groupFields.put("count", new BasicDBObject("$sum", 1));
    //             DBObject group = new BasicDBObject("$group", groupFields);

    //             DBObject projectFields = new BasicDBObject("_id", 0);
    //             projectFields.put("storeZip", "$_id");
    //             projectFields.put("productName", "$productName");
    //             projectFields.put("reviewCount", "$count");
    //             DBObject project = new BasicDBObject("$project", projectFields);

    //             DBObject sort = new BasicDBObject();
    //             sort.put("reviewCount", -1);

    //             DBObject orderby = new BasicDBObject();            
    //             orderby = new BasicDBObject("$sort",sort);


    //             AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

    //             for (DBObject result : aggregate.results()) {

    //                 BasicDBObject obj = (BasicDBObject) result;
    //                 Object o = com.mongodb.util.JSON.parse(obj.getString("storeZip"));
    //                 BasicDBObject dbObj = (BasicDBObject) o;
    //                 Review review = new Review(dbObj.getString("productName"), dbObj.getString("storeZip"),
    //                         obj.getString("reviewCount"), null);

    // 				// Review review =new Review(dbObj.getString("productName"),dbObj.getString("userName"),dbObj.getString("productType"),dbObj.getString("productMaker"),
    // 				// 				dbObj.getString("reviewRating"),dbObj.getString("reviewDate"),dbObj.getString("reviewText"),obj.getString("price"),
    // 				// 				dbObj.getString("storeId"),dbObj.getString("storeZip"),dbObj.getString("storeCity"),
    // 				// 				dbObj.getString("storeState"),dbObj.getString("productOnSale"),dbObj.getString("rebate"),
    // 				// 				dbObj.getString("userAge"),dbObj.getString("userGender"),dbObj.getString("userOccupation"));


    //                 reviewList.add(review);

    //             }
    //             return reviewList;

    //         }

    //         catch (

    //         Exception e) {
    //             reviewList = null;

    //             return reviewList;
    //         }

    //     }

    // //   //Get all the reviews grouped by product and zip code;
    // // public static ArrayList<Review> selectReviewForChart() {
    // //         ArrayList<Review> reviewList = new ArrayList<Review>();
    // //         try {
    // //             getConnection();
    // //             Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
    // //             dbObjIdMap.put("retailerpin", "$retailerpin");
    // //             dbObjIdMap.put("productName", "$productName");
    // //             DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
    // //             groupFields.put("count", new BasicDBObject("$sum", 1));
    // //             DBObject group = new BasicDBObject("$group", groupFields);

    // //             DBObject projectFields = new BasicDBObject("_id", 0);
    // //             projectFields.put("retailerpin", "$_id");
    // //             projectFields.put("productName", "$productName");
    // //             projectFields.put("reviewCount", "$count");
    // //             DBObject project = new BasicDBObject("$project", projectFields);

    // //             DBObject sort = new BasicDBObject();
    // //             sort.put("reviewCount", -1);

    // //             DBObject orderby = new BasicDBObject();            
    // //             orderby = new BasicDBObject("$sort",sort);


    // //             AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

    // //             for (DBObject result : aggregate.results()) {

    // //                 BasicDBObject obj = (BasicDBObject) result;
    // //                 Object o = com.mongodb.util.JSON.parse(obj.getString("retailerpin"));
    // //                 BasicDBObject dbObj = (BasicDBObject) o;
    // //                 Review review = new Review(dbObj.getString("productName"), dbObj.getString("retailerpin"),
    // //                         obj.getString("reviewCount"), null);
    // //                 reviewList.add(review);

    // //             }
    // //             return reviewList;
    // //         }

    // //         catch (

    // //         Exception e) {
    // //             reviewList = null;

    // //             return reviewList;
    // //         }

    // //     }



}