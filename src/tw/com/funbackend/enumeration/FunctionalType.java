package tw.com.funbackend.enumeration;

public enum FunctionalType {
	NewAuth {
		@Override
		public String toString() {
		
			return "NewAuth";
		}
	},
	UpdateAuth {
		@Override
		public String toString() {
		
			return "UpdateAuth";
		}
	},
	DeleteAuth {
		@Override
		public String toString() {
		
			return "DeleteAuth";
		}
	},
	QueryAuth {
		@Override
		public String toString() {
		
			return "QueryAuth";
		}
	}
}
