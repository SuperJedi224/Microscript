import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MicroscriptRunner{
	static ArrayList<Stack<Long>> stack;
	static long x=0,y=0;
	static Scanner in;
	static int stackId=0;
	static void run(String code){
		for(int i=0;i<code.length();){
			if(code.charAt(i)==';'||code.charAt(i)==']')i++;
			if(code.charAt(i)=='\''){
				x=(long)code.charAt(i+1);
				i+=2;continue ;
			}
			if("0123456789".indexOf(""+code.charAt(i))!=-1){
				int j=i;
				for(;i<code.length()&&"0123456789".indexOf(""+code.charAt(i))!=-1;i++){}
				x+=Long.parseLong(i>j?code.substring(j,i):"0");
			}else{
			if(code.charAt(i)=='e')x=(long)Math.pow(2, x);
			if(code.charAt(i)=='E')x=(long)Math.pow(10, x);
			if(code.charAt(i)=='f')stack.set(stackId, reverse(stack.get(stackId)));
			if(code.charAt(i)=='p')System.out.println(x);
			if(code.charAt(i)=='h'){in.close();System.exit(0);}
			if(code.charAt(i)=='n')System.out.println();
			if(code.charAt(i)=='s')stack.get(stackId).push(x);
			if(code.charAt(i)=='o')x=stack.get(stackId).pop();
			if(code.charAt(i)=='P')System.out.print((char)x);
			if(code.charAt(i)=='i')x=in.nextLong();
			if(code.charAt(i)=='z')x=0;
			if(code.charAt(i)=='C')for(long x:stack.get(stackId))stack.get((stackId+1)%2).push(x);
			if(code.charAt(i)=='t')x=stack.get(stackId).peek();
			if(code.charAt(i)=='+')x+=stack.get(stackId).pop();
			if(code.charAt(i)=='-')x+=stack.get(stackId).pop();
			if(code.charAt(i)=='*')x*=stack.get(stackId).pop();
			if(code.charAt(i)=='%')x%=stack.get(stackId).pop();
			if(code.charAt(i)=='/')x/=stack.get(stackId).pop();
			if(code.charAt(i)=='#')x=stack.get(stackId).size();
			if(code.charAt(i)=='Z')stack.get(stackId).clear();
			if(code.charAt(i)=='x')stackId=(stackId+1)%2;
			if(code.charAt(i)=='v')y=x;
			if(code.charAt(i)=='l')x=y;
			if(code.charAt(i)=='!')x=x==0?1:0;
			if(code.charAt(i)=='a'){
				while(!stack.get(stackId).isEmpty()){
					long l=stack.get(stackId).pop();
					System.out.print((char)l);
				}
			}
			if(code.charAt(i)=='q'){
				System.out.print('"');
				while(!stack.get(stackId).isEmpty()){
					long l=stack.get(stackId).pop();
					System.out.print((char)l);
				}
				System.out.print('"');
			}
			if(code.charAt(i)=='I'){
				String s=in.nextLine();
				for(int j=0;j<s.length();j++){
					stack.get(stackId).push((long)s.charAt(j));
				}
			}
			if(code.charAt(i)=='"'){
				int k=code.indexOf("\"", i+1);
				String s=code.substring(i+1,k);
				for(int j=0;j<s.length();j++){
					stack.get(stackId).push((long)s.charAt(j));
				}
				i=k;
			}
			if(code.charAt(i)=='['){
				int k=code.indexOf("]", i);
				String s=code.substring(i+1,k);
				if(stack.get(stackId).isEmpty())run(s);
				i=k+1;continue;
			}
			if(code.charAt(i)=='r'){
				i++;
				int j=i;
				for(;i<code.length()&&"0123456789".indexOf(""+code.charAt(i))!=-1;i++){}
				x+=(long)(Math.random()*Long.parseLong(i>j?code.substring(j,i):"0"));
				i--;
			}
			if(code.charAt(i)=='d'){
				i++;
				int j=i;
				for(;i<code.length()&&"0123456789".indexOf(""+code.charAt(i))!=-1;i++){}
				x-=Long.parseLong(i>j?code.substring(j,i):"0");
				i--;
			}
			if(code.charAt(i)=='{'){
				int j=i+1,k=1;
				while(k!=0&&j<code.length()){
					if(code.charAt(j)=='}')k--;
					if(code.charAt(j)=='{')k++;
					j++;
				}
				while(x!=0)run(code.substring(i+1, j));
				i=j-1;
			}
			if(code.charAt(i)=='c'){
				long k=x;
				int l=code.indexOf("]",i);
				if(l==-1)l=code.length();
				for(x=0;k>0;k--){
					run(code.substring(i+1,l));
				}
				i=l;
			}
			i++;
		}}
	}
	static Stack<Long> reverse(Stack<Long> s){
		Stack<Long> t=new Stack<Long>();
		while(!s.isEmpty()){
			t.push(s.pop());
		}
		return t;
	}
	public static void main(String[] args){
		stack=new ArrayList<Stack<Long>>()
				{static final long serialVersionUID = 1;{add(new Stack<Long>());add(new Stack<Long>());}};
		in=new Scanner(System.in);
		String code=(args.length>0?args[0]:in.nextLine()).replaceAll("\\$(.)", "c$1]");
		run(code);
		System.out.println(x);in.close();
	}
}