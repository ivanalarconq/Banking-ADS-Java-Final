package banking.transactions.domain.enumeration;

public enum TransactionType {
	DEBIT(1) {
		public String toString() {
			return "DEBIT";
		}
	},
	CREDIT(2) {
		public String toString() {
			return "CREDIT";
		}
	},
	TRANSFER(3) {
		public String toString() {
			return "TRANSFER";
		}
	};

	private final int code;

	private TransactionType(int code) {
		this.code = code;
	}
	
	public int getCode() {
        return this.code;
    }
}
