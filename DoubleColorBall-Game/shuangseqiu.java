import java.util.Scanner;
import java.util.Random;
public class shuangseqiu{
public static void main(String[] args){
	int[] winArr=creatNumber();
/*	System.out.println("==================");
	for(int i=0;i<winArr.length;i++){
		System.out.print(winArr[i]+" ");
	}
	System.out.print("\n");
	System.out.println("==================");*/
	int[] userInputArr=userInputNumber();
//	int[] countWinArr=countWinNumber(winArr,userInputArr);
//	System.out.println("红球中奖数量"+countWinArr[0]+"\n"+"蓝球中奖数量"+countWinArr[1]);
	switch(prizeResult(countWinNumber(winArr,userInputArr))){
		case 1->System.out.println("恭喜您中了一等奖，奖金最高为1000万元");
		case 2->System.out.println("恭喜您中了二等奖，奖金最高为500万元");
		case 3->System.out.println("恭喜您中了三等奖，奖金为3000元");
		case 4->System.out.println("恭喜您中了四等奖，奖金为200元");
		case 5->System.out.println("恭喜您中了五等奖，奖金为10元");
		case 6->System.out.println("恭喜您中了六等奖，奖金为5元");
		case 0->System.out.println("谢谢参与");
	}
}
public static int[] creatNumber(){
	int[] arr=new int[7];
	Random r=new Random();
	for(int i=0;i<6;){
		int redNumber=r.nextInt(33)+1;
		if(!contains(arr,redNumber)){
			arr[i]=redNumber;
			i++;
		}
	}
	arr[arr.length-1]=r.nextInt(16)+1;
	return arr;
}
public static int[] userInputNumber(){
	int[] arr=new int[7];
	Scanner sc=new Scanner(System.in);
	for(int i=0;i<6;){
		System.out.println("请输入第"+(i+1)+"个红球号码");
		int redNumber=sc.nextInt();
		if(legalInput(redNumber,33)){
			if(!contains(arr,redNumber)){
				arr[i]=redNumber;
				i++;
			}else{System.out.println("当前红球号码已经存在");}
		}else{System.out.println("输入的号码超出范围");}
	}
	System.out.println("请输入蓝球号码");
	while(true){
		int blueNumber=sc.nextInt();
		if(legalInput(blueNumber,16)){
			arr[arr.length-1]=blueNumber;
			break;
		}else{System.out.println("输入的号码超出范围");}
	}
	return arr;
}
public static boolean legalInput(int input,int maxNum){
	if(input>0&&input<=maxNum){
			return true;
		}else{return false;}
}
public static boolean contains(int[] arr,int number){
	for(int i=0;i<arr.length;i++){
		if(arr[i]==number){
			return true;
		}
	}
	return false;
}
public static int[] countWinNumber(int[] winArr,int[] userInputArr){
	int redCount=0;
	int blueCount=0;
	int arr[]=new int[2];
	for(int i=0;i<userInputArr.length-1;i++){
		int redNumber=userInputArr[i];
		for(int j=0;j<userInputArr.length-1;j++){
			if(redNumber==winArr[j]){
				redCount++;
				break;
			}
		}
	}
	int blueNumber=userInputArr[userInputArr.length-1];
		if(blueNumber==winArr[winArr.length-1]){
			blueCount++;
		}
	arr[0]=redCount;
	arr[1]=blueCount;
	return arr;
}
public static int prizeResult(int[] countWinArr){
	if(countWinArr[0]==6&&countWinArr[1]==1){return 1;}
	else if(countWinArr[0]==6&&countWinArr[1]==0){return 2;}
	else if(countWinArr[0]==5&&countWinArr[1]==1){return 3;}
	else if((countWinArr[0]==5&&countWinArr[1]==0)||(countWinArr[0]==4&&countWinArr[1]==1)){return 4;}
	else if((countWinArr[0]==4&&countWinArr[1]==0)||(countWinArr[0]==3&&countWinArr[1]==1)){return 5;}
	else if((countWinArr[0]==2&&countWinArr[1]==1)||(countWinArr[0]==1&&countWinArr[1]==1)||(countWinArr[0]==0&&countWinArr[1]==1)){return 6;}
	else{return 0;}
}
}