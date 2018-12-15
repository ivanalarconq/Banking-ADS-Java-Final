package banking.transactions.domain.enumeration;

public enum OperationType {
	DEBIT(1) {
		public String toString() {
			return "VALID";
		}
	},
	CREDIT(2) {
		public String toString() {
			return "INVALID";
		}
	};
	 

	private final int code;

	private OperationType(int code) {
		this.code = code;
	}
	
	public int getCode() {
        return this.code;
    }
}
