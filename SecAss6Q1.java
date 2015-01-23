
import acm.graphics.*;
import acm.program.*;

public class SecAss6Q1 extends GraphicsProgram {
	
	public void run() {
		GImage original = new GImage("Mona_Lisa.jpg");
		GImage flipped = flipHorizontal(original);
		flipped.scale(0.3);
		add(flipped);
	}
	
	private GImage flipHorizontal(GImage frog){
		
		int[][] pictureAsArray = frog.getPixelArray();
		int height = pictureAsArray.length;
		int length = pictureAsArray[0].length;
		
		int[][] newImage = new int[height][length];
		
		for (int i = 0;i<height;i++){
			for (int j = 0;j<length/2;j++){
				int pixel = pictureAsArray[i][j];
				int bizzaroPixel = pictureAsArray[i][length-j-1];
				newImage[i][length-j-1] = pixel;
				newImage[i][j] = bizzaroPixel;
			}
		}
		
		return new GImage(newImage);
	}
}
