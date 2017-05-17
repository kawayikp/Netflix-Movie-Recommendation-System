package coen272;

import static org.junit.Assert.*;

import java.util.List;

public class Test implements Base {

    private int[][] train = ReadFile.readTrainData(_FILE_TRAIN_PATH);

    private MyResult myresult5 = ReadFile.readTestData(_FILE_TEST5_PATH, 201);
    private int[][] test5 = myresult5._test;
    private List<Pair>[] rates5 = myresult5._index;

    private MyResult myresult10 = ReadFile.readTestData(_FILE_TEST10_PATH, 301);
    private int[][] test10 = myresult10._test;
    private List<Pair>[] rates10 = myresult10._index;

    private MyResult myresult20 = ReadFile.readTestData(_FILE_TEST20_PATH, 401);
    private int[][] test20 = myresult20._test;
    private List<Pair>[] rates20 = myresult20._index;

    /*
    /*----------------------user based with cosine similarity-----------------------------------------*/
    /*
    @org.junit.Test
    public void test1() {
    	System.out.println("user based with basic cosine similarity");
    	UB_Algorithm obj = new UB_Cosine_0(train, test5, rates5, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_1_PATH, 201);
    
    	obj = new UB_Cosine_0(train, test10, rates10, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_1_PATH, 301);
    
    	obj = new UB_Cosine_0(train, test20, rates20, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_1_PATH, 401);
    }
    */

    /*----------------------user based with advanced cosine similarity ------------------------------*/
    /*
    @org.junit.Test
    public void test2() {
    	System.out.println("user based with advanced cosine similarity");
    	UB_Algorithm obj = new UB_Cosine_best(train, test5, rates5, 1, 1, 2.5, 200, 0.5);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_1_PATH, 201);
    
    	obj = new UB_Cosine_best(train, test10, rates10, 1, 1, 2.5, 200, 0.5);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_1_PATH, 301);
    
    	obj = new UB_Cosine_best(train, test20, rates20, 1, 1, 2.5, 200, 0.5);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_1_PATH, 401);
    }
    */

    /*---------------------user based with basic pearson similarity------------------------------*/
    /*
    @org.junit.Test
    public void test3() {
    	System.out.println("user based with basic pearson similarity");
    	UB_Algorithm obj = new UB_Pearson_0(train, test5, rates5, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_2_PATH, 201);
    
    	obj = new UB_Pearson_0(train, test10, rates10, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_2_PATH, 301);
    
    	obj = new UB_Pearson_0(train, test20, rates20, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_2_PATH, 401);
    }
    */

    /*---------------------user based with advanced pearson similarity------------------------------*/
    /*
    @org.junit.Test
    public void test4() {
    	System.out.println("user based with advanced pearson similarity - can choose K users whose Math.abs(similairty) >= some value");
    	UB_Algorithm obj = new UB_Pearson_best(train, test5, rates5, 1, 1, 1.5, 200, 0, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_2_PATH, 201);
    
    	obj = new UB_Pearson_best(train, test10, rates10, 1, 2, 1.5, 200, 0, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_2_PATH, 301);
    
    	obj = new UB_Pearson_best(train, test20, rates20, 1, 4, 1.5, 200, 0, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_2_PATH, 401);
    }
    */

    /*---------------------user based with advanced pearson similarity------------------------------*/
    /*
    @org.junit.Test
    public void test5() {
    	System.out.println(
    			"user based with advanced pearson similarity - can choose users whose similarity within some range like: <= a negative value or >= 0");
    	UB_Algorithm obj = new UB_Pearson_best(train, test5, rates5, 1, 1, 1.5, 200, -0.6, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_2_PATH, 201);
    
    	obj = new UB_Pearson_best(train, test10, rates10, 1, 2, 1.5, 200, -0.6, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_2_PATH, 301);
    
    	obj = new UB_Pearson_best(train, test20, rates20, 1, 4, 1.5, 200, -0.6, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_2_PATH, 401);
    }
    */

    /*----------------user based with pearson similarity + IUF-------------------------------------  */
    /*
    @org.junit.Test
    public void test6() {
    	System.out.println(
    			"user based with pearson similarity + IUF: based on pearson which chose K neighbor whose similarity Math.abs(similarity) >= somevalue");
    	UB_Algorithm obj = new UB_Pearson_IUF_0(train, test5, rates5, 1, 1, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_3_PATH, 201);
    
    	obj = new UB_Pearson_IUF_0(train, test10, rates10, 1, 2, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_3_PATH, 301);
    
    	obj = new UB_Pearson_IUF_0(train, test20, rates20, 1, 4, 1.5, 200, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_3_PATH, 401);
    }
    */

    /*----------------user based with  pearson similarity + IUF-------------------------------------  */
    /*
    @org.junit.Test
    public void test7() {
    	System.out.println(
    			"user based with pearson similarity + IUF: based on pearson which chose users with specific similarity without k");
    	UB_Algorithm obj = new UB_Pearson_IUF_best(train, test5, rates5, 1, 1, 1.5, 200, -0.7, 0.0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_3_PATH, 201);
    
    	obj = new UB_Pearson_IUF_best(train, test10, rates10, 1, 2, 1.5, 200, -0.7, 0.0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_3_PATH, 301);
    
    	obj = new UB_Pearson_IUF_best(train, test20, rates20, 1, 4, 1.5, 200, -0.7, 0.0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_3_PATH, 401);
    }
    */

    /*---------------------user based with  pearson similarity + CM-------------------------*/
    /*
    @org.junit.Test
    public void test8() {
    	System.out.println("pearson CM advanced: based on pearson which choose similarity within some range without K ");
    	UB_Algorithm obj = new UB_Pearson_CM_0_best(train, test5, rates5, 1, 1, 1.5, 200, -0.7, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_4_PATH, 201);
    
    	obj = new UB_Pearson_CM_0_best(train, test10, rates10, 1, 2, 1.5, 200, -0.7, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_4_PATH, 301);
    
    	obj = new UB_Pearson_CM_0_best(train, test20, rates20, 1, 4, 1.5, 200, -0.7, 0);
    	obj.similarity();
    	obj.predict();
    	WriteFile.writeFileResult(obj._rates, _FILE_RESULT20_4_PATH, 401);
    }
    */

    /*------------------------item based with basic adjusted cosine method------------------------------*/
    /*
     @org.junit.Test 
     public void test9() { 
    	 System.out.println("item based with basic adjusted cosine method"); 
    	 IB_Algorithm obj = new IB_AdjustCosine_0(train,test5, rates5, 1, 1, 1.5, 200, 0); 
    	 obj.similarity(); obj.predict();
    	 WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_5_PATH, 201);
      
    	 obj = new IB_AdjustCosine_0(train, test10, rates10, 1, 1, 1.5, 200, 0);
    	 obj.similarity(); obj.predict(); 
    	 WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_5_PATH, 301);
      
    	 obj = new IB_AdjustCosine_0(train, test20, rates20, 1, 1, 1.5, 200, 0);
    	 obj.similarity(); obj.predict(); 
    	 WriteFile.writeFileResult(obj._rates,_FILE_RESULT20_5_PATH, 401); 
     }
     */

    /*------------------------item based with basic adjusted cosine method------------------------------*/
    /*
     @org.junit.Test 
     public void test10() { 
    	 System.out.println("item based with advanced adjusted cosine method which choose K neighbors with Math.abs(similarity) >= somevalue"); 
    	 IB_Algorithm obj = new IB_AdjustCosine_best(train,test5, rates5, 1, 20, 1.5, 200, 0); 
    	 obj.similarity(); obj.predict();
    	 WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_5_PATH, 201);
      
    	 obj = new IB_AdjustCosine_best(train, test10, rates10, 1, 20, 1.5, 200, 0);
    	 obj.similarity(); obj.predict(); 
    	 WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_5_PATH, 301);
      
    	 obj = new IB_AdjustCosine_best(train, test20, rates20, 1, 20, 1.5, 200, 0);
    	 obj.similarity(); obj.predict(); 
    	 WriteFile.writeFileResult(obj._rates,_FILE_RESULT20_5_PATH, 401); 
     }
     */

    /*------------------------item based with basic adjusted cosine method------------------------------*/
    /*
     @org.junit.Test 
     public void test11() { 
    	 System.out.println("item based with advanced adjusted cosine method which select specific similarity without k"); 
    	 IB_Algorithm obj = new IB_AdjustCosine_1(train,test5, rates5, 1, 50, 1.5, 200, -1, 0); 
    	 obj.similarity(); obj.predict();
    	 WriteFile.writeFileResult(obj._rates, _FILE_RESULT5_5_PATH, 201);
      
    	 obj = new IB_AdjustCosine_1(train, test10, rates10, 1, 50, 1.5, 200, -1, 0);
    	 obj.similarity(); obj.predict(); 
    	 WriteFile.writeFileResult(obj._rates, _FILE_RESULT10_5_PATH, 301);
      
    	 obj = new IB_AdjustCosine_1(train, test20, rates20, 1, 50, 1.5, 200, -1, 0);
    	 obj.similarity(); obj.predict(); 
    	 WriteFile.writeFileResult(obj._rates,_FILE_RESULT20_5_PATH, 401); 
     }
     */

    /*--------------------------------------combine  2 --------------------------------------------*/
    /*
     @org.junit.Test public void test12() { 
    	 System.out.println( "combine[cosine, person IUF]"); 
    	 UB_Algorithm obj1 = new UB_Cosine_best(train, test5, rates5, 1, 1, 25, 200, 0.5); 
    	 obj1.similarity();
    	 obj1.predict();
      
    	 UB_Algorithm obj2 = new UB_Pearson_IUF_best(train, test5, rates5, 1, 1, 1.5, 200, -1, 0); 
    	 obj2.similarity(); 
    	 obj2.predict();
    	 WriteFile.writeFileResultCombine(obj1._rates, 0.2, obj2._rates, 0.8, _FILE_RESULT5_6_PATH, 201);
      
    	 obj1 = new UB_Cosine_best(train, test10, rates10, 1, 1, 25, 200, 0.5); 
    	 obj1.similarity(); 
    	 obj1.predict();
      
    	 obj2 = new UB_Pearson_IUF_best(train, test10, rates10, 1, 2, 1.5, 200, -1, 0); 
    	 obj2.similarity(); 
    	 obj2.predict();
    	 WriteFile.writeFileResultCombine(obj1._rates, 0.2, obj2._rates, 0.8, _FILE_RESULT10_6_PATH, 301);
      
    	 obj1 = new UB_Cosine_best(train, test20, rates20, 1, 1, 25, 200, 0.5); 
    	 obj1.similarity(); 
    	 obj1.predict();
      
    	 obj2 = new UB_Pearson_IUF_best(train, test20, rates20, 1, 2, 1.5, 200, -1, 0); 
    	 obj2.similarity(); 
    	 obj2.predict();
    	 WriteFile.writeFileResultCombine(obj1._rates, 0.2, obj2._rates, 0.8, _FILE_RESULT20_6_PATH, 401); 
    }
    */

    /*--------------------------------------combine  2 --------------------------------------------*/
    /*
    @org.junit.Test public void test13() { 
     System.out.println( "combine[pearson IUF, item based]"); 
     IB_Algorithm obj1 = new IB_AdjustCosine_best(train,test5, rates5, 1, 20, 1.5, 200, 0); 
     obj1.similarity();
     obj1.predict();
     
     UB_Algorithm obj2 = new UB_Pearson_IUF_best(train, test5, rates5, 1, 1, 1.5, 200, -1, 0); 
     obj2.similarity(); 
     obj2.predict();
     WriteFile.writeFileResultCombine(obj1._rates, 0.4, obj2._rates, 0.6, _FILE_RESULT5_6_PATH, 201);
     
     obj1 = new IB_AdjustCosine_best(train,test10, rates10, 1, 20, 1.5, 200, 0); 
     obj1.similarity(); 
     obj1.predict();
     
     obj2 = new UB_Pearson_IUF_best(train, test10, rates10, 1, 2, 1.5, 200, -1, 0); 
     obj2.similarity(); 
     obj2.predict();
     WriteFile.writeFileResultCombine(obj1._rates, 0.4, obj2._rates, 0.6, _FILE_RESULT10_6_PATH, 301);
     
     obj1 = new IB_AdjustCosine_best(train,test20, rates20, 1, 20, 1.5, 200, 0); 
     obj1.similarity(); 
     obj1.predict();
     
     obj2 = new UB_Pearson_IUF_best(train, test20, rates20, 1, 2, 1.5, 200, -1, 0); 
     obj2.similarity(); 
     obj2.predict();
     WriteFile.writeFileResultCombine(obj1._rates, 0.4, obj2._rates, 0.6, _FILE_RESULT20_6_PATH, 401); 
    }
    */

    /*--------------------------------------combine  3 --------------------------------------------*/

    @org.junit.Test
    public void test13() {
        System.out.println("combine[cose, pearson IUF, item based]");

        IB_Algorithm obj1 = new IB_AdjustCosine_best(train, test5, rates5, 1, 20, 1.5, 200, 0);
        obj1.similarity();
        obj1.predict();
        UB_Algorithm obj2 = new UB_Pearson_IUF_best(train, test5, rates5, 1, 1, 1.5, 200, -1, 0);
        obj2.similarity();
        obj2.predict();
        UB_Algorithm obj3 = new UB_Cosine_best(train, test5, rates5, 1, 1, 25, 200, 0.5);
        obj3.similarity();
        obj3.predict();
        WriteFile.writeFileResultCombine(obj1._rates, 0.1, obj2._rates, 0.7, obj3._rates, 0.2, _FILE_RESULT5_6_PATH, 201);

        obj1 = new IB_AdjustCosine_best(train, test10, rates10, 1, 20, 1.5, 200, 0);
        obj1.similarity();
        obj1.predict();
        obj2 = new UB_Pearson_IUF_best(train, test10, rates10, 1, 2, 1.5, 200, -1, 0);
        obj2.similarity();
        obj2.predict();
        obj3 = new UB_Cosine_best(train, test10, rates10, 1, 1, 25, 200, 0.5);
        obj3.similarity();
        obj3.predict();
        WriteFile.writeFileResultCombine(obj1._rates, 0.1, obj2._rates, 0.7, obj3._rates, 0.2, _FILE_RESULT10_6_PATH, 301);

        obj1 = new IB_AdjustCosine_best(train, test20, rates20, 1, 20, 1.5, 200, 0);
        obj1.similarity();
        obj1.predict();
        obj2 = new UB_Pearson_IUF_best(train, test20, rates20, 1, 2, 1.5, 200, -1, 0);
        obj2.similarity();
        obj2.predict();
        obj3 = new UB_Cosine_best(train, test20, rates20, 1, 1, 25, 200, 0.5);
        obj3.similarity();
        obj3.predict();
        WriteFile.writeFileResultCombine(obj1._rates, 0.1, obj2._rates, 0.7, obj3._rates, 0.2, _FILE_RESULT20_6_PATH, 401);
    }
}
