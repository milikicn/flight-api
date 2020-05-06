package info.milikic.nikola.flightapi.vo;

public enum ErrorCode {

	UNAUTHORIZED,
	INVALID_CLIENT,
	PRINCIPAL_LOCKED,
	FORBIDDEN,

	USER_ALREADY_EXISTS,
	CITY_ALREADY_EXISTS,

	CITY_NOT_FOUND,
	COMMENT_NOT_FOUND,


	//Common error codes
	CONFLICT,
	PLATFORM_CONFLICT,
	BAD_REQUEST,
	SERVER_ERROR,
	NOT_IMPLEMENTED;
}