import java.util.ArrayList;

public class FileDownloaderManager {

	ArrayList<FileDownloader> fileDownloaderArray = new ArrayList<FileDownloader>();

	public void download(String[] urls) {
		for (String url : urls) {
			fileDownloaderArray.add(new FileDownloader(url));
		}
		while (downloadsAreReady() == false) {
			try {
				Thread.sleep(1000);
				printStatus();
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void printStatus() {
		for (FileDownloader fileDownloader : fileDownloaderArray) {
			System.out.println(fileDownloader.getStatus());
		}

	}

	public boolean downloadsAreReady() {
		for (FileDownloader fileDownloader : fileDownloaderArray) {
			if (fileDownloader.downloadIsReady() == false)
				return false;
		}
		return true;

	}

}
