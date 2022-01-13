import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader extends Thread {

	public String urlString;
	private String threadName;
	int size = 0;
	double sumCount = 0.0;
	private boolean downloadIsReady = false;

	public FileDownloader(String urlString) {
		this.urlString = urlString;
		this.start();
	}

	public double getPercentage() {
		return size == 0 ? 0 : (sumCount / size * 100.0);

	}

	public double getsumCount() {
		return this.sumCount;

	}

	public int getsize() {
		return this.size;
	}

	public boolean downloadIsReady() {
		return downloadIsReady;

	}

	@Override
	public void run() {
		threadName = Thread.currentThread().getName();
		String filename = urlString.substring(urlString.lastIndexOf("/") + 1).trim();
		System.out.println(Thread.currentThread().getName() + ": " + filename);
		BufferedInputStream in = null;
		FileOutputStream out = null;

		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			size = conn.getContentLength();

			in = new BufferedInputStream(url.openStream());

			out = new FileOutputStream(filename);
			byte data[] = new byte[1024];
			int count;

			while ((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);

				sumCount += count;

			}

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e4) {
					e4.printStackTrace();
				}
		}
		downloadIsReady = true;
	}

	public String getStatus() {
		return threadName + " : " + String.format("%,.2f", getPercentage()) + " %";
	}
}
