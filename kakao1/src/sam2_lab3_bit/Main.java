package sam2_lab3_bit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        o_map = new int[N][N];
        c_map = new int[N][N];
        spreadTime = new int[N][N];
        
        // selectedVirus[i][2]; i번째 바이러스의 y, x 저장 
        viruses = new int[10][2];
        vCnt = 0;
        
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                o_map[i][j] = Integer.parseInt(st.nextToken());
                if(o_map[i][j] == 2) {
                    viruses[vCnt][0] = i;
                    viruses[vCnt][1] = j;
                    vCnt++;
                }
            }
        }
        
        selectedVirus = new boolean[vCnt];
        selectVirus(0, 0);
        if(minAns != Integer.MAX_VALUE)
            System.out.println(minAns);
        else
            System.out.println(-1);
        
    }
    static int N, M, vCnt;
    static int[][] o_map, c_map;
    static int[][] viruses;
    
    static int[][] spreadTime;
    static boolean[] selectedVirus;
    static int minAns = Integer.MAX_VALUE;
    static void selectVirus(int si, int cnt) {
        if(cnt == M) {
            
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    c_map[i][j] = o_map[i][j];
                    spreadTime[i][j] = Integer.MAX_VALUE;
                }
            }
            
            Queue<Virus> q = new LinkedList<Virus>();
            for( int i = 0 ; i < vCnt ; i++ ) {
                if(selectedVirus[i]) {
                    q.add(new Virus(viruses[i][0], viruses[i][1]));
                    spreadTime[viruses[i][0]][viruses[i][1]] = 0;
                    c_map[viruses[i][0]][viruses[i][1]] = 1;
                }
            }
            
            Virus cur;
            int ny, nx;
            while(!q.isEmpty()) {
                cur = q.poll();
                for(int dir = 0 ; dir < 4 ; dir++) {
                    ny = cur.y + delta[dir][0];
                    nx = cur.x + delta[dir][1];
                    if(!(ny>=0 && ny < N &&  nx >= 0 && nx < N))
                        continue;
                    // 0 또는 2인 곳에 시간을 모두 작성 
                    if(c_map[ny][nx] != 1 && spreadTime[ny][nx] == Integer.MAX_VALUE) {
                        spreadTime[ny][nx] = spreadTime[cur.y][cur.x]+1;
                        q.add(new Virus(ny, nx));
                    }
                }
            }
            
            int max = 0;
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(c_map[i][j] != 0)
                        continue;
 
                    if(spreadTime[i][j] == Integer.MAX_VALUE)
                        return;
                    // 0 인 공간 중 가장 큰 시간 찾기 
                    if(max < spreadTime[i][j])
                        max = spreadTime[i][j];
                }
            }
            
            if(minAns > max) {
                minAns = max;
            }
            
            return;
        }
        
        if(si == vCnt) {
            return;
        }
        
        selectedVirus[si] = true;
        selectVirus(si+1, cnt+1);
        
        selectedVirus[si] = false;
        selectVirus(si+1, cnt);
    }
    
    static class Virus{
        int y, x;
        public Virus(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    static int[][] delta = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
}
