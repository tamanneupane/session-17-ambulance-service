package vastika.training.ambulance.utils;

public class Constants {

    private Constants(){}

    public static class StatusCode{
        public static int SUCCESS = 0;
        public static int ERROR = 1;
        public static int AMBULANCE_NOT_FOUND = 1000;
        public static int MISSING_PARAMETER = 1001;
        public static int GENERIC_ERROR = 2000;
    }

    public static class ErrorMessage {
        public static String AMBULANCE_NOT_FOUND_MESSAGE = "Not able to find the ambulance";
        public static String MISSING_PARAMETER_MESSAGE = "The parameter in the request is missing";
    }

}
