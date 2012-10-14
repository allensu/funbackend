package tw.com.funbackend.enumeration;

public enum UserInfoCategory {
	Admin {
		@Override
		public String toString() {
			return "Admin";
		}
	},
	Normal {
		@Override
		public String toString() {
			return "Normal";
		}
	},
	Guest {
		@Override
		public String toString() {
			return "Guest";
		}
	}	
}
