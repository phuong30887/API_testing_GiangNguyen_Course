package api.authentication;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.authentication.UserObject;

public class PostLogin {

    // về sau chỉ cần dùng biến POST_LOGIN ở những nơi khác, và chỉ cần khởi tạo 1 lần thôi
    public static final PostLogin POST_LOGIN = getInstance();
    public static final String ADMIN_TOKEN = POST_LOGIN.getToken(UserObject.admin());
    public static final String EDITOR_TOKEN = POST_LOGIN.getToken(UserObject.editor());

    public PostLogin() {

    }

    public static PostLogin getInstance() {
        return new PostLogin();
    }

    // để hàm này chỉ tạo 1 lần và dùng lâu dài :

    // để bên ngoài ko nhìn thấy function này => chuyển thành private
    private Response callAPI(UserObject userObject){
     return RestAssured.given().contentType(ContentType.JSON)
                .body(userObject)
                .get("https://run.mocky.io/v3/024a97bf-f8a9-47b2-b0ec-e4755a0d0e89");
    }

    public String getToken(UserObject userObject) {
        Response res = callAPI(userObject);
        return res.path("access_token");
    }

}
