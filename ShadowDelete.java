package delete;

import java.io.File;
import java.util.ArrayList;

import delete.DirectoryReader;

public class ShadowDelete {

	private static DirectoryReader book;
	private static DirectoryReader films;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ArrayList dlet = new ArrayList();
		

			File bookDirectory = new File("/home/dave/Desktop/book/MOVIES");				
			book = new DirectoryReader(bookDirectory);					
			ArrayList bookFiles = book.getList();
			
			System.out.println(bookFiles.size());
			
			File filmsDirectory = new File("/media/disk-1/Films");				
			films = new DirectoryReader(filmsDirectory);					
			ArrayList filmFiles = films.getList();
			
			System.out.println(filmFiles.size());

			for(int y=0;y<bookFiles.size();y++)				
			{
				String[] bf = bookFiles.get(y).toString().split("/");
				String bdf = bf[bf.length-1];
				
				for(int t=0;t<filmFiles.size();t++)				
				{					
					if(filmFiles.get(t).toString().indexOf(bdf)>-1)
					{
						String dirt;
						String[] dir = bookFiles.get(y).toString().split("home/dave/Desktop/book/MOVIES/");
						if(dir[1].indexOf('/')>-1)
						{
							String[] temp = dir[1].split("/");
							dirt = temp[0];
						}
						else
						{
							dirt = dir[0];
						}
						System.out.println("/home/dave/Desktop/book/MOVIES/" + dirt);
						
						if(dlet.contains("/home/dave/Desktop/book/MOVIES/" + dirt)==false)
						{
							dlet.add("/home/dave/Desktop/book/MOVIES/" + dirt);
						}
						
						
					}
				}					
			}		
		
			for(int p=0;p<dlet.size();p++)
			{
				System.out.println(dlet.get(p));
			}
	}

}
