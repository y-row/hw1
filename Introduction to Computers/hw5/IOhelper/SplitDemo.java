package IOhelper;
import java.util.ArrayList;
public class SplitDemo
{
   /* 切字串並轉成數字 */
	public static int stringToInt(String a)
   {
		int x=0;
		int scale=1;
		int strlen=a.length();
		//檢查是不是全部都是數字
		for(int i=0;i<strlen;i++)
		{
			if(a.charAt(i)<'0'||a.charAt(0)>'9')
			{
				return -1;
			}
		}
		//將String逐字分解，並存入宣告的x中
		for(int i=0;i<strlen;i++)
		{
			x+=scale*(int)(a.charAt(strlen-i-1)-'0');
			scale*=10;
		}
		return x;
	}
}
	
