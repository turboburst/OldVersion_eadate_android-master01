package com.somoplay.eadate.view.App;

/**
 * Created by turbo on 2016/6/22.
 */
public class Constances {

    public static final String URL = "http://192.168.2.10:8080";

    public static final String Token_url = URL + "/api/authenticate";
    public static final String Account_url = URL + "/api/account";
    public static final String ChangePassword_url = URL +"/api/account/change_password";
    public static final String ResetPasswordFinish_url = URL +"/api/account/reset_password/finish";
    public static final String ResetPasswordInit_url = URL +"/api/account/reset_password/init";
    public static final String Activate_url = URL +"/api/activate";
    public static final String Authenticate_url = URL +"/api/authenticate";
    public static final String Register_url = URL + "/api/register";
    public static final String AuthorDetail_url = URL + "/api/authorDetail/{id}";
    public static final String AuthorSearch_url = URL + "/api/_search/authors";
    public static final String Author_url = URL + "/api/authors";
    public static final String AuthorID_url = URL + "/api/authors{id}";
    public static final String BookSearch_url = URL + "/api/_search/books";
    public static final String Book_url = URL + "/api/books";
    public static final String BookID_url = URL + "/api/books/{id}";
    public static final String ComputerSearch_url = URL + "/api/_search/ly-computers";
    public static final String Computer_url = URL + "/api/ly-computers";
    public static final String ComputerID_url = URL + "/api/ly-computers/{id}";
    public static final String OwnerMoreID_url = URL + "/api/ly-owners-more/{id}";
    public static final String OwnerSearchID_url = URL + "/api/_search/ly-owners";
    public static final String Owner_url = URL + "/api/ly-owners";
    public static final String OwnerID_url = URL + "/api/ly-owners/{id}";
    public static final String StudentSearch_url = URL + "/aip/_search/ly-student";
    public static final String Student_url = URL + "/api/ly-student";
    public static final String StudentID_url = URL + "/api/ly-student/{id}";
    public static final String ProfileInfo_url = URL + "/api/profile-info";
    public static final String UserSearch_url = URL + "/api/_search/users/{query}";
    public static final String User_url = URL + "/api/users";
    public static final String UserLogin_url = URL + "/api/users/{login}";
}