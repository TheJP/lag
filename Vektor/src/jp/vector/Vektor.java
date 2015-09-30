package jp.vector;

public class Vektor {

	protected double[] x;

	public Vektor(double... x) {
		this.x = x;
	}

	public int getDimensions() {
		return x.length;
	}

	public double get(int i) {
		return x[i];
	}

	public void set(int i, double v) {
		x[i] = v;
	}

	public Vektor add(Vektor v) {
		return calc(v, new DoubleMather() {

			@Override
			public double calc(double lhs, double rhs) {
				return lhs + rhs;
			}
		});
	}

	public Vektor sub(Vektor v) {
		return calc(v, new DoubleMather() {

			@Override
			public double calc(double lhs, double rhs) {
				return lhs - rhs;
			}
		});
	}

	public Vektor calc(Vektor v, DoubleMather mather){
		int i;
		int min = Math.min(v.getDimensions(), getDimensions());
		int max = Math.max(v.getDimensions(), getDimensions());
		Vektor newVektor = new Vektor(new double[max]);
		for (i = 0; i < min; i++) {
			newVektor.set(i, mather.calc(v.get(i), get(i)));
		}
		for(; i < max; i++){
			if(getDimensions() == max){
				newVektor.set(i, get(i));
			}else{
				newVektor.set(i, v.get(i));
			}
		}
		return newVektor;
	}

	public double norm(){
		double result = 0.0;
		for(double d : x){
			result += (d*d);
		}
		return Math.sqrt(result);
	}

	/**
	 * Skalarprodukt
	 * @param v
	 * @return
	 */
	public double dot(Vektor v){
		int i;
		int min = Math.min(v.getDimensions(), getDimensions());
		int max = Math.max(v.getDimensions(), getDimensions());
		double result = 0.0;
		for (i = 0; i < min; i++) {
			result += v.get(i) * get(i);
		}
		for(; i < max; i++){
			if(getDimensions() == max){
				result += get(i);
			}else{
				result += v.get(i);
			}
		}
		return result;
	}

	public Vektor mul(double factor){
		Vektor newVektor  = new Vektor(this.x);
		for (int i = 0; i < getDimensions(); i++) {
			newVektor.set(i, factor * get(i));
		}
		return newVektor;
	}

	public Vektor normalize(){
		Vektor newVektor = new Vektor(x);
		double n = norm();
		for(int i = 0; i < getDimensions(); i++){
			newVektor.set(i, get(i) / n);
		}
		return newVektor;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(double d : x){
			sb.append(d);
			sb.append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		return sb.toString();
	}
}
