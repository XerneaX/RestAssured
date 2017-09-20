package responseTime;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
//import io.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.RequestSpecBuilder;
//import io.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by xernea on 20.09.2017.
 */
public class responseTimeTest {
    static RequestSpecBuilder reqBuilder;
    static RequestSpecification reqSpec;
    static ResponseSpecBuilder respBuilder;
    static ResponseSpecification respSpec;
    static Map<String,Object> responseHeaders = new HashMap<String, Object>();
    @BeforeClass
    public static void Init(){

        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
        reqBuilder = new RequestSpecBuilder();
        respBuilder = new ResponseSpecBuilder();
        reqBuilder.addParam("q","SELECT * FROM yahoo.finance.xchange WHERE pair in (\"EURUSD\",\"GBPUSD\")");
        reqBuilder.addParam("env","store://datatables.org/alltableswithkeys");
        reqBuilder.addParam("format","json");
        reqSpec = reqBuilder.build();
        respSpec = respBuilder.build();
        responseHeaders.put("Content-Type","application/json;charset=utf-8");
        responseHeaders.put("Server","ATS");



    }

    @Test
    public void getTime()
    {
        long time = given().spec(reqSpec)
                            .when().get("/yql").time();

        System.out.print(time);
    }

    @Test
    public void getTimeDifferenceKind(){

        long time = given().spec(reqSpec)
                            .when().get("/yql").timeIn(TimeUnit.SECONDS);

        System.out.print(time);

    }



}