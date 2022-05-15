package com.zkc.greedy;

import java.util.*;

//TODO
public class EventsArrange {
	
	public static void main(String[] args) {
//		int[][] events = new int[][]{new int[]{1, 10}, new int[]{2, 2}, new int[]{2, 2}, new int[]{2, 2}, new int[]{2, 2}}; //2
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{1, 2}}; //4
//		int[][] events = new int[][]{new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{2, 3}, new int[]{2, 3}}; //5
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 6}, new int[]{1, 2}, new int[]{1, 2}}; //3
//		int[][] events = new int[][]{new int[]{25, 27}, new int[]{22, 25}, new int[]{20, 24}, new int[]{8, 8}, new int[]{27, 27}}; //5
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{2, 2}, new int[]{3, 3}, new int[]{3, 4}, new int[]{3, 4}}; //4
//		int[][] events = new int[][]{new int[]{1, 4}, new int[]{4, 4}, new int[]{2, 2}, new int[]{3, 4}, new int[]{1, 1}}; // 4
//		int[][] events = new int[][]{new int[]{1, 4}, new int[]{2, 2}, new int[]{1, 1}}; // 3
//		int[][] events = new int[][]{new int[]{1, 2}, new int[]{3, 6}, new int[]{2, 6}, new int[]{9, 13}, new int[]{6, 6},
//				new int[]{25, 27}, new int[]{22, 25}, new int[]{20, 24}, new int[]{8, 8}, new int[]{27, 27}};  //10
//		int[][] events = new int[][]{new int[]{26, 27}, new int[]{24, 26}, new int[]{6, 6}, new int[]{25, 27}, new int[]{27, 27}}; //5
//		int[][] events = new int[][]{new int[]{34522, 98162}, new int[]{56705, 57927}, new int[]{7592, 48009}, new int[]{50550, 80558}, new int[]{33159, 78279}, new int[]{99678, 99930}, new int[]{90247, 95376}, new int[]{75266, 99543}, new int[]{91590, 98935}, new int[]{4483, 90998}, new int[]{33084, 92734}, new int[]{5068, 69346}, new int[]{34478, 77282}, new int[]{10678, 21394}, new int[]{33800, 89968}, new int[]{38246, 71779}, new int[]{64571, 94312}, new int[]{53702, 83978}, new int[]{32283, 53282}, new int[]{3829, 66048}, new int[]{6856, 79849}, new int[]{3404, 26978}, new int[]{54911, 83344}, new int[]{44310, 55309}, new int[]{97166, 97433}, new int[]{1782, 58327}, new int[]{5852, 74586}, new int[]{83219, 90280}, new int[]{2017, 49045}, new int[]{42374, 72047}, new int[]{87552, 99292}, new int[]{49145, 75522}, new int[]{41749, 64999}, new int[]{46798, 80680}, new int[]{84106, 86901}, new int[]{34719, 68723}, new int[]{1729, 30127}, new int[]{46952, 75651}, new int[]{95535, 98863}, new int[]{39603, 50253}, new int[]{84200, 90676}, new int[]{20586, 55674}, new int[]{13752, 85298}, new int[]{71576, 89702}, new int[]{42834, 68297}, new int[]{87151, 87665}, new int[]{64513, 99541}, new int[]{51479, 75754}, new int[]{55288, 95536}, new int[]{50879, 84146}, new int[]{84481, 88783}, new int[]{25776, 37769}, new int[]{85378, 85453}, new int[]{10527, 75976}, new int[]{67755, 93668}, new int[]{22510, 32702}, new int[]{50259, 58281}, new int[]{27094, 55050}, new int[]{77386, 86985}, new int[]{40958, 92384}, new int[]{72054, 74138}, new int[]{23514, 39124}, new int[]{67299, 67441}, new int[]{73916, 96694}, new int[]{35929, 78972}, new int[]{14172, 31996}, new int[]{83332, 85460}, new int[]{93314, 98761}, new int[]{12308, 53453}, new int[]{28009, 70668}, new int[]{65303, 75958}, new int[]{27214, 29495}, new int[]{81730, 94641}, new int[]{59937, 91701}, new int[]{7570, 40014}, new int[]{82199, 97180}, new int[]{43291, 89691}, new int[]{39299, 62524}, new int[]{34996, 42631}, new int[]{40366, 84499}, new int[]{45720, 83319}, new int[]{37609, 39126}, new int[]{55949, 62027}, new int[]{55026, 75913}, new int[]{80859, 81438}, new int[]{95347, 97878}, new int[]{78703, 82304}, new int[]{61278, 66898}, new int[]{39470, 90174}, new int[]{3868, 54203}, new int[]{84690, 93631}, new int[]{7727, 25471}, new int[]{30123, 82026}, new int[]{46906, 53791}, new int[]{95889, 98866}, new int[]{68029, 86026}, new int[]{14933, 54686}, new int[]{55237, 60769}, new int[]{29451, 32857}, new int[]{92900, 99852}, new int[]{29836, 40778}, new int[]{90705, 91003}, new int[]{65531, 85278}, new int[]{21037, 24060}, new int[]{74335, 84677}, new int[]{38959, 90811}, new int[]{83562, 95570}, new int[]{17283, 85438}, new int[]{13847, 64642}, new int[]{25125, 68143}, new int[]{72610, 78300}, new int[]{76055, 78927}, new int[]{17623, 26655}, new int[]{45860, 59689}, new int[]{4517, 37219}, new int[]{46805, 86439}, new int[]{55877, 65420}, new int[]{57012, 69228}, new int[]{53882, 60034}, new int[]{99477, 99640}, new int[]{97348, 99212}, new int[]{78942, 83751}, new int[]{95202, 98715}, new int[]{68469, 90625}, new int[]{57635, 96373}, new int[]{1636, 10805}, new int[]{21395, 97056}, new int[]{62631, 66397}, new int[]{66358, 92520}, new int[]{60551, 94571}, new int[]{94316, 98723}, new int[]{85815, 90733}, new int[]{30966, 43684}, new int[]{23333, 68847}, new int[]{72472, 97555}, new int[]{58336, 79773}, new int[]{45422, 95558}, new int[]{84313, 87741}, new int[]{39726, 95594}, new int[]{95227, 99704}, new int[]{72012, 92518}, new int[]{79292, 91813}, new int[]{14514, 61026}, new int[]{57879, 87348}, new int[]{30884, 75609}, new int[]{72844, 90365}, new int[]{67239, 98086}, new int[]{5696, 89898}, new int[]{41312, 64154}, new int[]{9382, 17093}, new int[]{4161, 94829}, new int[]{86865, 91800}, new int[]{37032, 97143}, new int[]{54582, 67784}, new int[]{7641, 15139}, new int[]{52500, 77060}, new int[]{83346, 84354}, new int[]{69934, 93881}, new int[]{66815, 92449}, new int[]{89763, 98430}, new int[]{33225, 57840}, new int[]{41671, 87846}, new int[]{47832, 59400}, new int[]{85408, 92578}, new int[]{16463, 46393}, new int[]{40713, 92124}, new int[]{53067, 68644}, new int[]{63753, 98004}, new int[]{31286, 94066}, new int[]{84496, 92696}, new int[]{28529, 47330}, new int[]{10374, 18421}, new int[]{31193, 96309}, new int[]{25407, 82890}, new int[]{40050, 89396}, new int[]{77430, 80967}, new int[]{50203, 98929}, new int[]{55778, 70061}, new int[]{35819, 90268}, new int[]{31598, 72210}, new int[]{68879, 85755}, new int[]{23901, 32936}, new int[]{29364, 36876}, new int[]{60949, 73388}, new int[]{58166, 82855}, new int[]{95942, 96771}, new int[]{86548, 90421}, new int[]{34456, 53355}, new int[]{40946, 55404}, new int[]{94088, 96030}, new int[]{83431, 93702}, new int[]{72615, 81782}, new int[]{29266, 44641}, new int[]{10131, 85347}, new int[]{15939, 43460}, new int[]{45981, 73539}, new int[]{15709, 69405}, new int[]{34200, 74152}, new int[]{9244, 22181}, new int[]{71828, 97403}, new int[]{10819, 26721}, new int[]{70606, 91387}, new int[]{18676, 44334}, new int[]{94401, 98983}, new int[]{30103, 36190}, new int[]{29629, 45167}, new int[]{42576, 48583}, new int[]{65257, 67831}, new int[]{13015, 98902}, new int[]{58225, 96516}, new int[]{34620, 74835}, new int[]{66602, 78732}, new int[]{32256, 70820}, new int[]{49103, 60890}, new int[]{39291, 68734}, new int[]{59273, 87516}, new int[]{97464, 99401}, new int[]{60123, 76819}, new int[]{5998, 15639}, new int[]{15337, 57047}, new int[]{84640, 91594}, new int[]{39329, 81675}, new int[]{77710, 89476}, new int[]{8737, 15112}, new int[]{32898, 95936}, new int[]{14966, 78397}, new int[]{90168, 97143}, new int[]{92348, 95873}, new int[]{91797, 97094}, new int[]{3439, 94722}, new int[]{38746, 94632}, new int[]{47246, 97578}, new int[]{65721, 97775}, new int[]{20234, 28148}, new int[]{6408, 33584}, new int[]{90515, 94396}, new int[]{77992, 82615}, new int[]{92245, 99916}, new int[]{69265, 74782}, new int[]{29850, 60659}, new int[]{15961, 54145}, new int[]{56893, 75818}, new int[]{99597, 99605}, new int[]{38206, 83274}, new int[]{6808, 60349}, new int[]{80328, 95160}, new int[]{17226, 50570}, new int[]{878, 17142}, new int[]{60036, 83980}, new int[]{40086, 55123}, new int[]{31124, 50807}, new int[]{39617, 88439}, new int[]{88465, 92047}, new int[]{62754, 70164}, new int[]{50994, 99466}, new int[]{7483, 28709}, new int[]{43053, 92235}, new int[]{57599, 81135}, new int[]{4301, 54289}, new int[]{18808, 75854}, new int[]{76901, 86141}, new int[]{80299, 81257}, new int[]{2628, 5958}, new int[]{11620, 62855}, new int[]{35655, 85662}, new int[]{22485, 98188}, new int[]{18486, 44092}, new int[]{72766, 76769}, new int[]{49545, 76242}, new int[]{61920, 92403}, new int[]{36775, 47277}, new int[]{78924, 85685}, new int[]{77964, 79979}, new int[]{53911, 69270}, new int[]{14049, 48455}, new int[]{13274, 22986}, new int[]{88707, 91886}, new int[]{15591, 69158}, new int[]{76886, 98201}, new int[]{90450, 90955}, new int[]{42312, 60764}, new int[]{24565, 90790}, new int[]{68329, 76552}, new int[]{73938, 74215}, new int[]{25632, 57524}, new int[]{8342, 34443}, new int[]{90356, 96154}, new int[]{99685, 99687}, new int[]{46214, 65194}, new int[]{93023, 93481}, new int[]{41032, 59330}};
//		int[][] events = new int[][]{new int[]{2, 10}, new int[]{2, 10},
//				new int[]{3, 10}, new int[]{3, 10}, new int[]{4, 10},
//				new int[]{7, 8}, new int[]{8, 9}, new int[]{9, 10}, new int[]{12, 12}, new int[]{12, 12}}; //9
		int[][] events = new int[][]{new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 5}, new int[]{1, 10}, new int[]{1, 1}, new int[]{1, 4}, new int[]{5, 5}};
//		int[][] events = new int[][]{new int[]{1, 1}, new int[]{1, 2}, new int[]{1, 3}, new int[]{1, 4}, new int[]{1, 5}};//5
		
		System.out.println(bestArrange(events));
	}
	
	
	private static int bestArrange(int[][] events) {
		
		Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
		
		Set<int[]> handledEvent = new HashSet<>();
		Set<Integer> handledDay = new HashSet<>();
		int count = 0;
		int visitDay = 0;
		for (int[] event : events) {
			if (event[0] > visitDay) {
				visitDay = event[0];
			}
			int[] target = findContainsAndNearest(visitDay, events, handledEvent, handledDay);
			if (target != null) {
				count++;
				handledDay.add(visitDay);
				handledEvent.add(target);
				visitDay++;
			}
		}
		return count;
	}
	
	
	private static int[] findContainsAndNearest(int visitDay, int[][] events, Set<int[]> handled, Set<Integer> handledDay) {
		Set<int[]> filteredSet = new HashSet<>();
		for (int[] event : events) {
			if (handled.contains(event) || handledDay.contains(visitDay) || (event[0] > visitDay || event[1] < visitDay)) {
				continue;
			}
			filteredSet.add(event);
		}
		
		int distance = Integer.MAX_VALUE;
		int[] min = null;
		for (int[] event : filteredSet) {
			int start = event[0];
			int end = event[1];
			if (start <= visitDay) {
				if (end - visitDay < distance) {
					min = event;
					distance = end - visitDay;
				}
			}
		}
		return min;
	}
	
	private static int backup(int[][] events) {
		
		Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
		List<int[]> ealierAndShorterSort = new ArrayList<>(Arrays.asList(events));
		int start = ealierAndShorterSort.get(0)[0];
		List<int[]> curLenSort = new ArrayList<>();
		int differStart = 1;
		int curLenSortAdd = 0;
		for (int i = 0; i < ealierAndShorterSort.size(); i++) {
			int[] event = ealierAndShorterSort.get(i);
			int curStart = event[0];
			if (curStart == start) {
				curLenSort.add(curLenSortAdd++, event);
			}
			if (curStart != start || i == ealierAndShorterSort.size() - 1) {
				curLenSort.sort(Comparator.comparingInt(e -> e[1] - e[0]));
				if (curStart != start) {
					differStart++;
					i--;
					start = curStart;
				}
				for (int j = 0; j < curLenSortAdd; j++) {
					ealierAndShorterSort.set(i - (curLenSortAdd - j - 1), curLenSort.get(j));
				}
				curLenSortAdd = 0;
				curLenSort.clear();
			}
		}
		return 0;
	}
}
