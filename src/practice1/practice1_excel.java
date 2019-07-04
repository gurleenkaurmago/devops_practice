package practice1;

import java.io.FileInputStream;
import java.io.FileReader;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class practice1_excel {

	public static void main(String[] args) {
		
		
		try 
		{
			FileInputStream fs = new FileInputStream("C:\\Users\\welcome\\Desktop\\selenium_practice1.xls");
			HSSFWorkbook work = new HSSFWorkbook(fs);
			HSSFSheet sheet = work.getSheet("Sheet1");
			
			int rc,cc;
			rc = sheet.getPhysicalNumberOfRows();
			
			for(int i=1;i<rc;i++)
			{
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell = row.getCell(0);
				
				if(cell==null || cell.getStringCellValue().trim().isEmpty())
				{
					System.out.println("login id is empty , login unsuccessful");
					continue;
					
				}
				if(row.getCell(1)==null || row.getCell(1).getStringCellValue().trim().isEmpty())
				{
					System.out.println("password is empty, login unsuccessful ");
					continue;
				}
				WebDriver driver =new ChromeDriver();
				driver.get("https://www.google.com/");
				
				driver.findElement(By.linkText("Gmail")).click();
				driver.findElement(By.xpath("/html/body/nav/div/a[2]")).click();
				
				String Beforeurl=(driver.getCurrentUrl());
				
				
				//cell.getStringCellValue();
				driver.findElement(By.id("identifierId")).sendKeys(cell.getStringCellValue());
				driver.findElement(By.id("identifierNext")).click();
				
				Thread.sleep(5000); //1000 =1 sec
				
				String Afterurl=(driver.getCurrentUrl());
				if(Beforeurl.equals(Afterurl))
				{
					System.out.println("incorrect email id , login unsuccessful ");
					driver.close();
				}
				
				else
				{
					cell = row.getCell(1);
				
					Beforeurl = driver.getCurrentUrl();
					
					driver.findElement(By.name("password")).sendKeys(cell.getStringCellValue());
					driver.findElement(By.id("passwordNext")).click();
				
					Thread.sleep(5000); //1000 =1 sec
					Afterurl= driver.getCurrentUrl();
					
					if(Beforeurl.equals(Afterurl))
						System.out.println(" Incorrect password, login unsuccessful ");
					else 
						{
						System.out.println("Login successful");
						driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a/span")).click();
													//*[@id="gb"]/div[2]/div[3]/div/div[2]/div/a/span												
						driver.findElement(By.linkText("Sign out")).click();
						System.out.println("logged out ");
						}
																				
					driver.close();
				
				
				}
			}
			System.out.println("Hi.... testing");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
