package FloorStoreService;


    public class OrderDuplicateException extends Exception{
        public OrderDuplicateException(String message) {
            super(message);
        }

        public OrderDuplicateException(String message, Throwable cause) {
            super(message, cause);
        }
    }

