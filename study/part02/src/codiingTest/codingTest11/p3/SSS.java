package codiingTest.codingTest11.p3;

public class SSS {
    static boolean result;

    public static boolean solution(String s, String[] words) {
        result = false;
        dfs(s, words);
        return result;
    }

    static void dfs(String s, String[] words) {
        if (s.equals("")) {
            result = true;
            return;
        }

        for (String w: words) {
            if (s.startsWith(w)) {
                dfs(s.substring(w.length()), words);
            }
        }
    }

    public static void main(String[] args) {
        String[] ss = new String[]{"zer", "ro", "ze", "base"};
//        System.out.println(solution("zerobase", ss));

//        ss = new String[]{};
//        System.out.println(solution("", ss));

        ss = new String[]{"zkmzqqlqk", "bsexoue", "qiywn", "eppdhwdbvv", "eppdhwdbvv", "ouq", "s", "brsspwbp", "zvhb", "qrk", "lvh", "shg", "iilcbxhghm", "robpnc", "wh", "koxpbawsm", "dfjwefaj", "nqqc", "dfjwefaj", "fa", "fhacw", "ylg", "yksibssqsz", "ikmiqjx", "yksibssqsz", "tdujrl", "rxc", "oztthnsb", "ewbq", "akst", "wjjnkrcv", "th", "wnjxqu", "ucqo", "ovsvac", "majmbdg", "nqqc", "axgezhj", "rxc", "fczg", "qdpgye", "anadss", "wrazg", "ouq", "d", "lfaz", "bnhpprol", "yd", "th", "ygqnh", "oubap", "aozxghftdk", "ikmiqjx", "rspbr", "uyiul", "cydul", "q", "rse", "ylg", "rublcriqjp", "egmk", "nwyhoubf", "yarizg", "agozbvymtl", "egmk", "qwtnhcetp", "brjr", "yfdziahpgg", "gaes", "bd", "yksibssqsz", "qi", "cnn", "lvh", "bnhpprol", "fczg", "zyrcxoy", "hzvjmomn", "u", "s", "nrgqebxwli", "nolqfam", "axgezhj", "uumx", "pa", "g", "noptyunkh", "vm", "rnyj", "dtbxcqi", "majmbdg", "lazbvr", "oztthnsb", "dz", "wjk", "klugdxpe", "brsspwbp", "eiwixnugg", "odv", "vm"};
        System.out.println(solution("rnyjeppdhwdbvvdmajmbdglazbvrdzzyrcxoybrjrwrazgakstbrsspwbpoubapdfjwefajbrjraxgezhjqwtnhcetpovsvacvmbrsspwbpouqoubapshgylg", ss));
    }
}
