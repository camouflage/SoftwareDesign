package com.example.myticket.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.myticket.R;
import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Location;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.ScreeningRoom;
import com.example.myticket.entities.User;

import android.R.integer;
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class MyApplication extends Application{
	private User _u;
	private Map<String, Integer> _photo_map;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
		String phone = sharedPreferences.getString("phone", " ");
		_u = DataBaseHelper.getInstance(getApplicationContext()).queryUser(phone);
		_photo_map = new HashMap<String, Integer>();
		_photo_map.put("photo1", new Integer(R.drawable.photo1));
		_photo_map.put("photo2", new Integer(R.drawable.photo2));
		_photo_map.put("photo3", new Integer(R.drawable.photo3));
		_photo_map.put("photo4", new Integer(R.drawable.photo4));
		_photo_map.put("photo5", new Integer(R.drawable.photo5));
		initData();
	}

	// setUser
	public void setUser(User user) {
		this._u = user;
	}

	// getUser
	public User getUser() {
		return _u;
	}

	// getPhotoMap()
	public Map<String, Integer> getPhotoMap() {
		return _photo_map;
	}

	// Initialization
	private void initData() {
		DataBaseHelper dbHelper = DataBaseHelper.getInstance(getApplicationContext());
		// Init cinima
		Cinema[] c = new Cinema[4];
		c[0] = new Cinema();
		c[0].setCinema_id("c1");
		c[0].setCinema_name("青宫电影城");
		c[0].setDist_code("440104 510180");

		c[1] = new Cinema();
		c[1].setCinema_id("c2");
		c[1].setCinema_name("正佳飞扬电影城");
		c[1].setDist_code("440106 510510");
		
		c[2] = new Cinema();
		c[2].setCinema_id("c3");
		c[2].setCinema_name("江高数字影院");
		c[2].setDist_code("440111 510440");
		
		c[3] = new Cinema();
		c[3].setCinema_id("c4");
		c[3].setCinema_name("市二宫电影院");
		c[3].setDist_code("440105 510220");

		for (int i = 0; i < c.length; i++) {
			dbHelper.addCinema(c[i]);
		}
		
//		ArrayList<Cinema> cs = dbHelper.queryAllCinema();
//		for (Cinema cinema : cs) {
//			Log.i("test", cinema.toString());
//		}


		// Init location
		dbHelper.addLocation(new Location("440106 510510", "广东省广州市天河区"));
		dbHelper.addLocation(new Location("440103 510145", "广东省广州市荔湾区"));
		dbHelper.addLocation(new Location("440104 510180", "广东省广州市越秀区"));
		dbHelper.addLocation(new Location("440105 510220", "广东省广州市海珠区"));
		dbHelper.addLocation(new Location("440111 510440", "广东省广州市白云区"));
		dbHelper.addLocation(new Location("440112 510700", "广东省广州市黄埔区"));
		dbHelper.addLocation(new Location("440113 511400", "广东省广州市番禺区"));
		dbHelper.addLocation(new Location("440114 510800", "广东省广州市花都区"));
		

		ScreeningRoom[] sr = new ScreeningRoom[5];
		sr[0] = new ScreeningRoom("s1", "A", 10, 8);
		sr[1] = new ScreeningRoom("s2", "B", 12, 10);
		sr[2] = new ScreeningRoom("s3", "C", 8, 7);
		sr[4] = new ScreeningRoom("s5", "E", 10, 8);
		sr[3] = new ScreeningRoom("s4", "D", 13, 9);
		// init ScreeningRoom
		for (int i = 0; i < sr.length; i++) {
			dbHelper.addScreeningRoom(sr[i]);
		}



		// init Movie
		Movie[] m = new Movie[5];
		m[0] = new Movie();
		m[1] = new Movie();
		m[2] = new Movie();
		m[3] = new Movie();
		m[4] = new Movie();

		m[0].setMovie_id("m[0]");
		m[0].setActors("郭富城，梁家辉，杨采妮，文咏珊，周润发，彭于晏，李治廷，杨祐宁");
		m[0].setDescription("刘杰辉（郭富城饰）荣升警队一哥后，他的妻儿却遭遇了歹徒绑架。虽然费尽心机要救回妻儿，但最终他的妻子还是死于歹徒之手。刘忍受失去至爱的伤痛，誓要抓住歹徒，惩戒恶人。他化悲愤为力量，一心要揪出绑架案的幕后大老板。可是，单凭一个人的能力要对付这伙穷凶极恶、阴险狡诈之徒，着实是个不小的难题与挑战。思虑再三，他决心说服已经退休的前警务处副处长李文彬（梁家辉饰）再度复出，协助于他破案。随着对案件的深入调查，他们发现了谁都不曾预料到的事。李文彬的儿子竟然与幕后大老板有着十分紧密的联系。之后，这四人之间少不了的便是一番斗智斗勇。");
		
		m[0].setDirector("陆剑青、梁乐民");
		m[0].setDuration(110);
		m[0].setLanguages("汉语普通话、粤语");
		m[0].setMovie_name("寒战2");
		m[0].setPhoto("photo1");
		m[0].setPoint(new Float(7.2));
		m[0].setSale_account(79961);
		m[0].setTag("剧情、动作、犯罪");

		m[1].setMovie_id("m[1]");
		m[1].setActors("亚历山大·斯卡斯加德，玛歌特·罗比，克里斯托弗·瓦尔兹，塞缪尔·杰克逊，杰曼·翰苏");
		m[1].setDescription("19世纪80年代，泰山（亚历山大·斯卡斯加德饰）已经离开了刚果丛林十年了，他与心爱的珍·波特（玛歌特·罗比饰）在维多利亚女王时代的英国伦敦生活。看似平静的城市生活对泰山来说并非真的自在，甚至感到窒息，身着考究的礼服，住在豪宅里，却找不到家的感觉。一次，他以英国议会贸易大使的身份重新回到刚果，但这次派遣实际上是一次比利时人里昂·罗姆（克里斯托弗·瓦尔兹饰）的阴谋。泰山、珍以及他的朋友们都陷入了危险，但丛林是泰山的天下，他开始重新飞檐走壁，拯救爱人和朋友。");
		m[1].setDirector("大卫·叶茨");
		m[1].setDuration(109);
		m[1].setLanguages("英语");
		m[1].setMovie_name("泰山归来：险战丛林");
		m[1].setPhoto("photo2");
		m[1].setPoint(new Float(8.3));
		m[1].setSale_account(52764);
		m[1].setTag("动作、冒险");

		m[2].setMovie_id("m[2]");
		m[2].setActors("吴亦凡，刘亦菲，金世佳，李沁，李梦，郝邵文");
		m[2].setDescription("外表温柔安静的苏韵锦（刘亦菲饰）在高中同学程铮（吴亦凡饰）的深情追求下慢慢爱上了对方，可无奈相爱容易相处难，自小生活环境的差异以及性格的迥异开始成为两人之间的问题，矛盾的不断发现最终还是让感情出现了裂痕最后走到了分手境地。几年后，苏韵锦成为了一个事业有成的职场女强人，但平静的生活因程铮的再次出现而泛起了涟漪，两人剪不断理还乱的感情促使他们又纠缠到了一起，并且这时才知道彼此在这几年中都没有放下，想爱但又怕过去重演。");
		m[2].setDirector("周拓如");
		m[2].setDuration(98);
		m[2].setLanguages("汉语普通话");
		m[2].setMovie_name("致青春：原来你还在那里");
		m[2].setPhoto("photo3");
		m[2].setPoint(new Float(4.0));
		m[2].setSale_account(23238);
		m[2].setTag("爱情、青春、剧情");
		
		m[3].setMovie_id("m[3]");
		m[3].setActors("包贝尔，宋佳，朱亚文，焦俊艳");
		m[3].setDescription("陆垚（包贝尔 饰）在上大学时重逢幼儿园同学马俐（宋佳 饰），虽然彼此心存好感，但由于陆垚有严重的“表白障碍症”，只能眼巴巴看着自己的女神马俐与别人谈恋爱。而自此之后很多年，陆垚只能以朋友的名义爱着马俐，也与她开始了一段“友情不甘、恋人不敢”的长跑。");
		m[3].setDirector("文章");
		m[3].setDuration(107);
		m[3].setLanguages("汉语普通话");
		m[3].setMovie_name("陆垚知马俐");
		m[3].setPhoto("photo4");
		m[3].setPoint(new Float(5.1));
		m[3].setSale_account(36728);
		m[3].setTag("喜剧 、爱情");
		
		m[4].setMovie_id("m[4]");
		m[4].setActors("季冠霖，苏尚卿，许魏洲，金士杰");
		m[4].setDescription("所有人类的灵魂都是海里一条巨大的鱼，出生的时候从海的此岸出发，在路途中，有时相遇，有时分开，死的时候去到海的彼岸，之后变成一条沉睡的小鱼，等待多年后的再次出发，这个旅程永远不会结束，生命往复不息。十六岁生日那天，居住在“神之围楼”里的一个名叫椿的女孩变作一条海豚到人间巡礼，被大海中的一张网困住，一个人类男孩因为救她而落入深海死去。为了报恩，为了让人类男孩复活，她需要在自己的世界里，历经种种困难与阻碍，帮助死后男孩的灵魂——一条拇指那么大的小鱼，成长为一条比鲸更巨大的鱼并回归大海。");
		m[4].setDirector("梁旋、张春");
		m[4].setDuration(115);
		m[4].setLanguages("汉语普通话");
		m[4].setMovie_name("大鱼海棠");
		m[4].setPhoto("photo5");
		m[4].setPoint(new Float(6.2));
		m[4].setSale_account(62953);
		m[4].setTag("动画、奇幻");
		for (int i = 0; i < m.length; i++) {
			dbHelper.addMovie(m[i]);
		}
		Random random = new Random();
		for (int i = 1; i <= 80; i++) {
			int sr_index = random.nextInt(sr.length);
			ProductDescription p = new ProductDescription("p"+Integer.toString(i), 
					m[random.nextInt(m.length)].getMovie_id(), 
					c[random.nextInt(c.length)].getCinema_id(),
					sr[sr_index].getScreening_room_id());
			p.setPrice((float)(random.nextInt(41) + 60));
			p.setType("3D");
			StringBuilder sb = new StringBuilder("1");
			for (int j = 1; j < sr[sr_index].getCol() * sr[sr_index].getRow(); j++) {
				sb.append(",1");
			}
			String s = sb.toString();
			p.setSeat_availible(s);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR_OF_DAY, random.nextInt(11) - 5);
			calendar.add(Calendar.MINUTE, random.nextInt(60));
			calendar.add(Calendar.DAY_OF_MONTH, random.nextInt(3));
			p.setStartTime(calendar.getTime()); 
			dbHelper.addProductDescription(p);
		}


	}
}
