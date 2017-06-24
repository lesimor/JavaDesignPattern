package step2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/* 문제 해결의 핵심은 성적 통보 대상이 변경되더라도 ScoreRecord 클래스를 수정없이 그대로 재사용할 수 있어야 함.
 * ScoreRecord 클래스에서 변화되는 부분을 식별하고 이를 일반화시켜야함.
 * */
interface Observer {
	public abstract void update();
}

abstract class Subject {
	// 문제점 2(여러 종류의 view를 다룰 경우)에 대한 해결.
	// -> 데이터의 변경을 통보할 옵저버들을 여러개 가질 수 있게 수정.
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer observer){	// 통보 대상을 추가.
		observers.add(observer);
	}
	
	public void detach(Observer observer){	// 통보 대상을 제거.
		observers.remove(observer);
	}
	
	// 통보 대상 옵저버들에게 데이터의 변경을 통보.
	public void notifyObservers(){
		for(Observer o:observers){
			o.update();
		}
	}
}

// 좀더 다양한 형태의 데이터에 대응할 수 있음.
class ScoreRecord extends Subject{
	private List<Integer> scores = new ArrayList<Integer>();
	
	public void addScore(int score){
		scores.add(score);
		
		// 데이터가 변경되면 Subject 클래스의 notifiyObservers  메서드를 호출해 
		// 각 옵저버(통보 대상 클래스)에게 데이터의 변경을 통보.
		notifyObservers();
	}
	
	public List<Integer> getScoreRecord(){
		return scores;
	}
	
}

// DataSheetView는 Observer의 기능. 즉 update메서드를 구현합으로써 통보 대상이 됨.
class DataSheetView implements Observer{
	private ScoreRecord scoreRecord;
	private int viewCount;
	
	public DataSheetView(ScoreRecord scoreRecord, int viewCount){
		this.scoreRecord = scoreRecord;
		this.viewCount = viewCount;
	}
	
	public void update() {	// 점수의 변경을 통보받음.
		List<Integer> record = scoreRecord.getScoreRecord();	// 점수를 조회.
		displayScores(record, viewCount);					// 조회된 점수를 viewCount 만큼 출력..
	}
	
	// 문제점1(성적을 다른 형태로 출력하고 싶다면?)에 대한 해결. 
	// -> 인터페이스를 이용해 서로 다른 출력 형태의 클래스를 분리.
	private void displayScores(List<Integer> record, int viewCount){
		System.out.println("List of " + viewCount + " entries: ");
		for(int i = 0; i < viewCount && i < record.size(); i++){
			System.out.println(record.get(i) + " ");
		}
		System.out.println();
	}
}

// 다양한 형태의 출력 클래스를 만들 수 있다. 
class MinMaxView implements Observer {
	private ScoreRecord scoreRecord;
	
	public MinMaxView(ScoreRecord scoreRecord) {
		this.scoreRecord = scoreRecord;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		List<Integer> record = this.scoreRecord.getScoreRecord();
		displayMinMax(record);
	}
	
	private void displayMinMax(List<Integer> record){
		System.out.println("최대: " + Collections.max(record));
		System.out.println("최소: " + Collections.min(record));
	}
	
}
public class ObserverStep2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreRecord scoreRecord = new ScoreRecord();
		
		// 3개까지만 점수 출력하는 뷰.
		DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
		
		scoreRecord.attach(dataSheetView);
		
		// 최대 최소 값을 출력하는 뷰
		MinMaxView minMaxView = new MinMaxView(scoreRecord);
		
		scoreRecord.attach(minMaxView);
		
		// 점수 갱신 및 출력 확인.
		scoreRecord.addScore(3);
		scoreRecord.addScore(4);
		scoreRecord.addScore(5);
		scoreRecord.addScore(6);
	}

}