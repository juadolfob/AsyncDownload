
public class Main {

	public static void main(String[] args) {

		FileDownloaderManager fileDownloaderManager = new FileDownloaderManager();
		String[] urls = { 
				"https://iweb.dl.sourceforge.net/project/reactos/ReactOS/0.4.14/ReactOS-0.4.14-iso.zip",
				"https://iweb.dl.sourceforge.net/project/reactos/ReactOS/0.4.13/ReactOS-0.4.13-iso.zip" };
		fileDownloaderManager.download(urls);

	}
}
