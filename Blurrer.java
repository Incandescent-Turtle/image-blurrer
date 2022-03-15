import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Blurrer
{
	BufferedImage og;
	BufferedImage blurred;

	public Blurrer()
	{
		try {
			og = ImageIO.read(new File("img.jpg"));
			blurred = ImageIO.read(new File("img.jpg"));

			long start = System.currentTimeMillis();
			for (int x = 0; x < og.getWidth(); x++)
			{
				if(x%100 == 0)
					System.out.println(x);
				for (int y = 0; y < og.getHeight(); y++)
				{
					int r = 10;
					int red = 0;
					int blue = 0;
					int green = 0;

					int neighbours = 1;
					for (int k = -r; k <= r; k++)
					{
						for (int l = -r; l <= r; l++)
						{
							if(!isInCircle(x, y, r,x+k, y+l) || (k==0 && l==0) || (x+k < 0 || x+k > og.getWidth()-1 || y+l < 0 || y+l > og.getHeight()-1))
							{
								continue;
							}
							int rgb = og.getRGB(x+k, y+l);
							red   += (rgb >> 16) & 0xFF;
							green += (rgb >> 8) & 0xFF;
							blue  += rgb & 0xFF;
							neighbours++;
						}
					}
					blurred.setRGB(x, y, ((red/neighbours & 0xFF) << 16) | ((green/neighbours & 0xFF) << 8)  | ((blue/neighbours & 0xFF) << 0));
				}
			}
			ImageIO.write(blurred, "jpg", new File("blurred.jpg"));
			System.out.println("output");
			System.out.println((System.currentTimeMillis()-start)/1000);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static boolean isInCircle(int xOrigin, int yOrigin, int r, int x, int y)
	{
		int dx = x - xOrigin;
		int dy = y - yOrigin;

		int distanceSquared = dx * dx + dy * dy;

		int radiusSquared = r * r;

		if (distanceSquared <= radiusSquared) {
			return true;
		}
		return false;
	}

	public static void main(String[] args)
	{
		new Blurrer();
	}
}