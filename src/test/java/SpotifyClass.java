
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SpotifyClass {


    String tokenValue = "Bearer BQCndiEe7Pnm7z8V0N62DUsQbM6ZKCOdiCe86CfDzxApWp0kPgh5qH-Uqdx19fIHgpiabgIyoNYJSf5dg5y8X3ngn0LXjv__BAdD2shnF42d9rLcE51trqR6-7nfeILLmIvV9tR4S1L6nXiqg_EW7ZQelUzM4CSqH_ahkIZRvodJQB1z6wKMSKr84WIzUVpaZ8KU_TAAt_7ruz9GOXO_8ns-VPeGMKElXWiGYRFQ8khlbzWrRb9tatY4JdznHPpYs29TgmmuKe9pTMIgi-evuLaX9-Szr_c5Jg";
    String userId=" ";
    @Test
    public void givenCodingCafeLogin_WhenTokenIsCorrect_ShouldReturnSuccessCode() throws ParseException {

        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
                ResponseBody body=response.getBody();
                JSONObject object=(JSONObject) new JSONParser().parse(body.prettyPrint());
                userId=(String) object.get("id");
                   // response.prettyPrint();
                response.then().assertThat().statusCode(200);
    }

    @Test
    public void givenCodingCafeLogin_WhenTokenIsCorrect_shouldReturnSuccesscode(){

        Response response = RestAssured.given()
                    .accept("application/json")
                    .contentType("application/json")
                    .header("Authorization", tokenValue)
                    .when()
                    .get("https://api.spotify.com/v1/users/userId");
                    response.prettyPrint();
                    response.then().assertThat().statusCode(200);
        }


    @Test
    public void givenUserID_GetPlayList() throws ParseException {
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/users/31f2szl4xa2xzixaqysculg6nqru/playlists");

        JSONObject object = (JSONObject) new JSONParser().parse((response.asString()));
      /*  Object total = object.get("total");*/
        String asString=response.asString();

        JsonPath jsonPath = new JsonPath(asString);
        int total=jsonPath.getInt("total");
        System.out.println("total==>" + total);
        response.prettyPrint();


    }


    @Test
     public void givenCodingCafeLogin_WhenTokenIsCorrect_playlistshouldbeCreated(){
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\":\"collection of oldSong\",\"description\":\"old playlist description\",\"public\":\"false\"}")
                .when()
                .post("https://api.spotify.com/v1/users/31f2szl4xa2xzixaqysculg6nqru/playlists");
                int statusCode = response.getStatusCode();
                response.prettyPrint();
                response.then().assertThat().statusCode(201);
    }


    @Test
    public void givenUserID_AfterAddingTheJson_GetPlayList() throws ParseException {
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/users/31f2szl4xa2xzixaqysculg6nqru/playlists");

        JSONObject object = (JSONObject) new JSONParser().parse((response.asString()));
        /*  Object total = object.get("total");*/
        String asString=response.asString();

        JsonPath jsonPath = new JsonPath(asString);
        int total=jsonPath.getInt("total");
        System.out.println("total==>" + total);
        response.prettyPrint();


    }










}