package codiingTest.codingTest19.p1;


public class Solution {

	public static boolean solution(String param0, String param1) {
		int splitPoint = -1;
		int len = param1.length();

		if (param0.length() != len) {
			return false;
		}

		while (splitPoint != len - 1) {
			for (int i = splitPoint + 1; i < len; i++) {
				if (param0.charAt(0) == param1.charAt(i)) {
					splitPoint = i;
					break;
				}
			}

			if (param0.startsWith(param1.substring(splitPoint)) &&
				param1.startsWith(param0.substring(len - splitPoint))) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		String s1 = "pfzymrffhs";
		String s2 = "ymrffhspfz";
//		System.out.println(solution(s1, s2));



		s1 = "zhgskodwxtyiahxmfdelywtteufnaxdprqrjuyqzwbrcrjwbybbadzvxerushzsrhktnmxkwbnsashqqdygdlbctcoxlvvebuwwsnyttxbtpagsnohowzotcdovddmhdelueubgraiefxesxgwiupjxsiohhyzexsaclxwglyfckrwwaedpkxpprfxwvuebjsfwwsmdvemtqgwiklsbqmujozbumhhfvfznhupwdraekihotknufzajvgiezwcodafsnhpcicvjsikogoubfamcnenhtvymxwizyetrqshvseztbaersywffckljvpuwtnknlrwrgoldclysbxspaiimdqpqmocvwwtghbuwypuvdbwzsqkwhbubsqclhuuieilpyidqltrovfuemooclhhkkfryyegkpspmzyugtaysylsgyeahrnuruybchdosemgaggrzbmmqdyhpkowqiyeyrgzwnvchmyjzpsudxpdduoywoktzhndbbjqwtzgwijozmbjordhenkintqtkydbcmifaubqzgpwlxaoumvzvmrwqbfpdeznzvukgbfmpdljwhvqyhhmgpynfbqlajuqiyzafpifuxmzoqnuxnmuvredjbjjddqxfskaycmahjjnzplvlsltcestjbpckvuvctaztuwvtybcsduktcostegwunastqzqvghzzcqrpkjxdctakorgqrvmylxkshqtbucudgigpjvjuinpjuqorfiwsmzysdkkohmxccnajguenwrkeytrggwvvhsvxsbbhuibmeksnonzyndsdaclwmunsntjguuuruorrkawwilcyblawsuorxchcmbqpsvbblnsiynmrxtxecapsfpfpzwhbgnilnxjkioennuoximjasszbwuxdgtrcazsjmehycyujtoqokytknhdhwfgazgescusbufctyjhnuzrjmwgjezuguyhihwlwktymewzkwzzxliggheaidtkz";
		s2 = "cqrpkjxdctakorgqrvmylxkshqtbucudgigpjvjuinpjuqorfiwsmzysdkkohmxccnajguenwrkeytrggwvvhsvxsbbhuibmeksnonzyndsdaclwmunsntjguuuruorrkawwilcyblawsuorxchcmbqpsvbblnsiynmrxtxecapsfpfpzwhbgnilnxjkioennuoximjasszbwuxdgtrcazsjmehycyujtoqokytknhdhwfgazgescusbufctyjhnuzrjmwgjezuguyhihwlwktymewzkwzzxliggheaidtkzzhgskodwxtyiahxmfdelywtteufnaxdprqrjuyqzwbrcrjwbybbadzvxerushzsrhktnmxkwbnsashqqdygdlbctcoxlvvebuwwsnyttxbtpagsnohowzotcdovddmhdelueubgraiefxesxgwiupjxsiohhyzexsaclxwglyfckrwwaedpkxpprfxwvuebjsfwwsmdvemtqgwiklsbqmujozbumhhfvfznhupwdraekihotknufzajvgiezwcodafsnhpcicvjsikogoubfamcnenhtvymxwizyetrqshvseztbaersywffckljvpuwtnknlrwrgoldclysbxspaiimdqpqmocvwwtghbuwypuvdbwzsqkwhbubsqclhuuieilpyidqltrovfuemooclhhkkfryyegkpspmzyugtaysylsgyeahrnuruybchdosemgaggrzbmmqdyhpkowqiyeyrgzwnvchmyjzpsudxpdduoywoktzhndbbjqwtzgwijozmbjordhenkintqtkydbcmifaubqzgpwlxaoumvzvmrwqbfpdeznzvukgbfmpdljwhvqyhhmgpynfbqlajuqiyzafpifuxmzoqnuxnmuvredjbjjddqxfskaycmahjjnzplvlsltcestjbpckvuvctaztuwvtybcsduktcostegwunastqzqvghzz";
		System.out.println(solution(s1, s2));

	}
}