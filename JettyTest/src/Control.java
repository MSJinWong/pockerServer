import java.util.*;

/**
 *纸牌花色的枚举类
 */
enum FlowerColor{
	Hearts,Clubs,Spade,Diamonds;
}

/**
 *纸牌点数的枚举类
 */

enum Number{
 	TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),
	NINE(9),TEN(10),JACK(11),QUEEN(12),KING(13),ACE(14);
	private int value;
	private Number(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
 }

/**
*纸牌类
*/
class Card implements Comparable<Card>{
	private FlowerColor fc;//代表花色
	private Number num; //代表点数
	
	
	public Card(){
		fc = FlowerColor.Hearts;
		num = Number.ACE;
	}
	public Card(FlowerColor fc,Number num){
		this.fc = fc;
		this.num = num;
	}
	
	public Number getNum(){
		return num;
	}

	public FlowerColor getFc(){
		return fc;
	}
	
	public String toString(){
		String flowerColor = "";
		String number = "";
		switch(fc){
			case Hearts : 
				flowerColor = "红桃";
				break;
			case Clubs :
				flowerColor = "梅花";
				break;
			case Spade :
				flowerColor = "黑桃";
				break;
			case Diamonds :
				flowerColor = "方块";
				break;
			
			
		}
		switch(num){
			case TWO: number="2";break;
			case THREE: number="3";break;
			case FOUR: number="4";break;
			case FIVE: number="5";break;
			case SIX: number="6";break;
			case SEVEN: number="7";break;
			case EIGHT: number="8";break;
			case NINE: number="9";break;
			case TEN: number="10";break;
			case JACK: number="J";break;
			case QUEEN: number="Q";break;
			case KING: number="K";break;
			case ACE: number="A";break;
		}
		return flowerColor+number;
	}
		
	public int compareTo(Card other){
		if(this.num.getValue()>other.num.getValue())
			return 1;
		else if(this.num.getValue()<other.num.getValue())
			return -1;
		else
			return 0;
	}

	public boolean equals(Object o){
		Card c = null;
		if(o instanceof Card)
			c = (Card)o;
		if(this.fc==c.fc&&this.num.getValue()==c.num.getValue())
			return true;
		return false;
	}
	
	//随机的生成一张纸牌的方法
	public static Card randomCreateCard(){
		Card c = new Card();
		Random rand = new Random();
		int color = rand.nextInt(4);
		int number = rand.nextInt(13);
		
			
		switch(color){
			case 0 : 
				c.fc = FlowerColor.Hearts;
				break;
			case 1 :
				c.fc = FlowerColor.Clubs;
				break;
			case 2 :
				c.fc = FlowerColor.Spade;
				break;
			case 3 :
				c.fc = FlowerColor.Diamonds;
				break;
			
		}
		switch(number){
			case 0: c.num = Number.TWO; break;
			case 1: c.num = Number.THREE; break;
			case 2: c.num = Number.FOUR; break;
			case 3: c.num = Number.FIVE; break;
			case 4: c.num = Number.SIX; break;
			case 5: c.num = Number.SEVEN; break;
			case 6: c.num = Number.EIGHT; break;
			case 7: c.num = Number.NINE; break;
			case 8: c.num = Number.TEN; break;
			case 9: c.num = Number.JACK; break;
			case 10: c.num = Number.QUEEN; break;
			case 11: c.num = Number.KING; break;
			case 12: c.num=Number.ACE; break;
		}

		return c;
	}
	
		
}

/**
 *代表一副完整的扑克
 */
class WholeCard{
	private Card[] cards; //拥有52张纸牌
	private int length = 52; //牌的多少
	
	public WholeCard(){
		createWholeCards();	
	}
	//创建一副崭新的扑克
	private void createWholeCards(){
		length = 52;
		cards = new Card[length];
		FlowerColor[] fc = {FlowerColor.Hearts,FlowerColor.Clubs,FlowerColor.Spade,FlowerColor.Diamonds};
		Number[] num = 
			{Number.TWO,Number.THREE,Number.FOUR,
			 Number.FIVE,Number.SIX,Number.SEVEN,
			 Number.EIGHT,Number.NINE,Number.TEN,
			 Number.JACK,Number.QUEEN,Number.KING,Number.ACE};
		int tmp = 0;
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				cards[tmp++] = new Card(fc[i],num[j]);
			}
		}
	}
	
	//重设的方法
	public void reset(){
		createWholeCards();
	}
	
	//从一副扑克任意的分发一张扑克的方法
	public Card randomDispatchCard(){
		boolean flag = true;
		Card c;
		do{
			c = Card.randomCreateCard();
			for(int i=0;i<length;i++){
				if(c.equals(cards[i])){
					cards[i] = cards[length-1];
					cards[length-1] = null;
					length--;
					flag = false;
				}
			}
		}while(flag);	

		return c;
	}

	public int getLength(){
		return length;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++){
			sb.append(cards[i]);
			if(i!=length-1)
				sb.append("\n");
		}
		return sb.toString();
	}
}
/**
 *代表玩家类
 */

class Player{
	private String name; //玩家的姓名
	private double money = 0.0; //玩家所拥有的金钱
	private Card[] cards = new Card[3]; //玩家拥有的三张牌
	private int victorCount = 0; //玩家根据自己的三张牌计算得到一个获胜的点数
	public Player(){}
	public Player(String name){
		this.name = name;
	}	
	
	public Card[] getCards(){
		return cards;
	}
	
	public Player(String name,double money){
		this.name = name;
		this.money = money;
	}
	public void setMoney(double money){
		this.money = money;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public double getMoney(){
		return money;
	}

	public int getVictorCount(){
		return victorCount;
	}

	public void setVictorCount(int victorCount){
		this.victorCount = victorCount;
	}

	
	public String toString(){
		return name+":"+cards[0]+","+cards[1]+","+cards[2];
	}
}
/**
 *代表扑克游戏类
 */
class PokeGame{
	private  WholeCard wc = new WholeCard(); //代表所有的玩家共有一副扑克
	private Player[] players = new Player[4]; //四个玩家
	
	
	//根据四个玩家的名字构造出四个人来玩的游戏
	public PokeGame(String[] playersName){
		for(int i=0;i<4;i++)
			players[i] = new Player(playersName[i]);
	}
	
	public PokeGame(Player[] ps){
		for(int i=0;i<4;i++){
			players[i] = ps[i];
		}
	}
	
	public Player[] getPlayers(){
		return players;	
	}

	public void reset(){
		wc.reset();
	}
	
	
	public Player getPlayer(String name){
		for(int i=0;i<4;i++){
			if(name.equals(players[i].getName()))
				return players[i];
		}
		return null;
	}

	public WholeCard getWholeCard(){
			return wc;
	}
	
	//给四个玩家每人发三张牌的方法
	public void autoDispatchCard(){
		for(int i=0;i<3;i++){
			for(int j=0;j<4;j++){
				players[j].getCards()[i] = wc.randomDispatchCard();
				System.out.println(players[j]+","+players[j].getCards()[i]);
			}
		}
		sortEveryPlayerCards();		
	}
	
	//计算某个玩家的牌所能获得的点数	
	private int calculateVictorCount(Card[] cards,Player player){
		//如果没有初始化三张牌则返回0
		for(int i=0;i<cards.length;i++){
			if(cards[i]==null){
				player.setVictorCount(0);
				return player.getVictorCount();
			}
		}
		int fc[] = new int[cards.length];
		for(int i=0;i<cards.length;i++){
			fc[i] = cards[i].getFc().ordinal();
		}
		int[] values = new int[cards.length];
		for(int i=0;i<cards.length;i++){
			values[i] = cards[i].getNum().getValue();
		}
		StringBuilder sb = new StringBuilder();
		if(values[0]==values[1]&&values[1]==values[2]){  //炸弹
			sb.append("6").append(changeNum(values[0])).append(changeNum(values[1])).append(changeNum(values[2]));
			int vc = Integer.parseInt(sb.toString());
			player.setVictorCount(vc);
			return vc;
		} //同花顺
		else if((values[0]+1)==values[1]&&(values[1]+1)==values[2]&&fc[0]==fc[1]&&fc[1]==fc[2]){
			sb.append("5").append(changeNum(values[0])).append(changeNum(values[1])).append(changeNum(values[2]));
			int vc = Integer.parseInt(sb.toString());
			player.setVictorCount(vc);
			return vc; 
		}else if(fc[0]==fc[1]&&fc[1]==fc[2]){	//同花
			sb.append("4").append(changeNum(values[0])).append(changeNum(values[1])).append(changeNum(values[2]));
			int vc = Integer.parseInt(sb.toString());
			player.setVictorCount(vc);
			return vc;
		}else if((values[0]+1)==values[1]&&(values[1]+1)==values[2]){ //顺子	
			sb.append("3").append(changeNum(values[0])).append(changeNum(values[1])).append(changeNum(values[2]));
			int vc = Integer.parseInt(sb.toString());
			player.setVictorCount(vc);
			return vc;
		}else if(values[0]==values[1]||values[1]==values[2]||values[0]==values[2]){ //对子
			sb.append("2").append(changeNum(values[0])).append(changeNum(values[1])).append(changeNum(values[2]));
			int vc = Integer.parseInt(sb.toString());
			player.setVictorCount(vc);
			return vc; 
		}else{ //杂牌
			sb.append("1").append(changeNum(values[0])).append(changeNum(values[1])).append(changeNum(values[2]));
			int vc = Integer.parseInt(sb.toString());
			player.setVictorCount(vc);
			return vc; 	
		}
					
	}
	//用于实现数字加零的效果（如果数字小于10就在前面加零，否则不加零）
	private String changeNum(int num){
		StringBuilder sb = new StringBuilder();
		if(num<10){
			sb.append(0).append(num);
			return sb.toString();
		}
		return sb.append(num).toString();
	}
	//用于计算所有的玩家中谁的获胜的点数大
	private int max(int[] vc){
		int max = vc[0];
		for(int i=1;i<vc.length;i++){
			if(vc[i]>max)
				max = vc[i];
		}
		return max;
	}
	//得到获胜的人
	public Player getWinner(){
		Player p = null;
		int[] scores = new int[players.length];
		for(int i=0;i<players.length;i++){
			scores[i] = calculateVictorCount(players[i].getCards(),players[i]);
		}
		int max = max(scores);
		for(int i=0;i<players.length;i++){
			if(max==players[i].getVictorCount()){
				p = players[i];
				players[i].setMoney(players[i].getMoney()+1000);
			}else{
				players[i].setMoney(players[i].getMoney()-1000);
			}
		}
		return p;
	}

	//把每个玩家的三张牌进行排序的方法
	private void sortEveryPlayerCards(){
		for(int i=0;i<4;i++){
			Arrays.sort(players[i].getCards());
			
		}
	}
	
	//显示当前游戏中的四个玩家的信息	
	public void showAllPlayersInfo(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<4;i++){
			sb.append(players[i]);
			if(i!=3)
				sb.append('\n');
		}
		System.out.println(sb); 
	}

	public void showAllPlayersMoney(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<4;i++){
			sb.append(players[i].getName()+":"+players[i].getMoney());
			if(i!=3)
				sb.append('\n');
		}
		System.out.println(sb); 
	}
	
	
}

public class Control{
	public static void main(String[] args){
		//1.创造四个玩扑克游戏的玩家
		Player[] players = {new Player("张三",10000),
							new Player("李四",20000),
							new Player("王五",30000),
							new Player("赵六",25000)
							};
							
		//2.创建一个新的扑克游戏（利用这四个准备好的玩家）
		PokeGame game = new PokeGame(players);

		//3.游戏自动给每个玩家发三张纸牌
		game.autoDispatchCard();

		//4.游戏判断哪个玩家赢
		Player winner = game.getWinner();

		//5.输出每个玩家的信息和胜利者的信息
		System.out.println("======扑克游戏========");
		game.showAllPlayersInfo(); 
		System.out.println(winner.getName()+"赢啦"); 
		game.showAllPlayersMoney();
		System.out.println("======扑克游戏========");
	}
}

