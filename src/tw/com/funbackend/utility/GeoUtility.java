package tw.com.funbackend.utility;

public class GeoUtility {
	public double GetDistance(double Lat1, double Long1, double Lat2,
			double Long2) {
		double Lat1r = ConvertDegreeToRadians(Lat1);
		double Lat2r = ConvertDegreeToRadians(Lat2);
		double Long1r = ConvertDegreeToRadians(Long1);
		double Long2r = ConvertDegreeToRadians(Long2);

		double R = 6371; // Earth's radius (km)
		double d = Math
				.acos(Math.sin(Lat1r) * Math.sin(Lat2r) + Math.cos(Lat1r)
						* Math.cos(Lat2r) * Math.cos(Long2r - Long1r))
				* R;
		return d;
	}

	private double ConvertDegreeToRadians(double degrees) {
		return (Math.PI / 180) * degrees;
	}
}
