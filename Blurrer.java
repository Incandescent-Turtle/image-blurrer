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

			for (int x = 0; x < og.getWidth(); x++)
			{
				if(x%100 == 0)
					System.out.println(x);
				for (int y = 0; y < og.getHeight(); y++)
				{
					var colors = new ArrayList<Color>();

					int r = 20;
					for (int k = -1; k <= r; k++)
					{
						for (int l = -1; l <= r; l++)
						{
							if(!isInCircle(x, y, r,x+k, y+l) || (k==0 && l==0) || (x+k < 0 || x+k > og.getWidth()-1 || y+l < 0 || y+l > og.getHeight()-1))
							{
								continue;
							}
							colors.add(new Color(og.getRGB(x+k, y+l)));
						}
					}
					blurred.setRGB(x, y, averageColor(colors).getRGB());
				}
			}
			ImageIO.write(blurred, "jpg", new File("blurred.jpg"));
			System.out.println("output");

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static Color averageColor(ArrayList<Color> colors)
	{
		int red = 0;
		int blue = 0;
		int green = 0;

		for (Color c : colors)
		{
			red += c.getRed();
			blue += c.getBlue();
			green += c.getGreen();
		}

		return new Color(red/colors.size(), green/colors.size(), blue/colors.size());
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