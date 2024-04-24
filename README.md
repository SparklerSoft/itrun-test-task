some samples request on Postman:

http://127.0.0.1:8080/v1/users - get all users from DB

http://127.0.0.1:8080/v1/users/create - add user into DB
the Json body is:
{
    "type": "type",
    "firstName": "name",
    "lastName": "lastName",
    "mobile": "number",
    "email": "email",
    "pesel": "pesel"
}

http://127.0.0.1:8080/v1/users/10 - get user by user ID

http://127.0.0.1:8080/v1/users/type/internal - get all users with selected TYPE

http://127.0.0.1:8080/v1/users/search - search users on the selected params
