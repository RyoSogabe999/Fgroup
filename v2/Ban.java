import java.util.*;

public class Ban {
	int r;
	int l;
	int [][]map = new int [8][8];//黒0,白1,置ける場所2,空白3

	public Ban(){
		this.r = 8;
		this.l = 8;
	}

	public void initMap() {
		for(int i=0;i<8;i++) {
			Arrays.fill(map[i], 3);//空白で埋める
		}

		//中央4マスの配置
		map[3][3] = 1;
		map[3][4] = 0;
		map[4][3] = 0;
		map[4][4] = 1;
		//先手が黒なので黒に対する”置ける場所”を探索する
		checkMap(0);
		printMap(0);
	}

	public void printSign(int p){
		if(p==0){
			System.out.print("● ");
		}else if(p==1){
			System.out.print("○ ");
		}else if(p==2){
			System.out.print("* ");
		}else{
			System.out.print("　");
		}
	}

	public void printMap(int p) {
		System.out.println("====================");
		System.out.println(" | 0 1 2 3 4 5 6 7");
		System.out.println("-+------------------");
		checkMap(p);
		for(int i=0; i<8;i++) {
			System.out.print(String.valueOf(i)+"| ");
			for(int j=0; j<8; j++) {
				printSign(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("====================");
	}

	public void updateMap(int r, int l, int p) throws ArithmeticException {//r:行, l:列, p:置かれた色
		/*
		if(map[r][l] != 2) {//置ける場所以外に置こうとしたとき,空白に置こうとしたときも含む
			System.out.println("this place has been occupied");
			throw new ArithmeticException();
		} else if(p != 0 && p != 1){//手番情報が黒(0),白(1)以外のとき
			System.out.println("invalid value");
			throw new ArithmeticException();
		} else */ {
			map[r][l] = p;

			//置いた場所の周囲8マスを探索
			//0が置かれたら1を、1が置かれたら0を探索する
			int target = 1 - p;//探索対象

			//探索方向を指定してilistに格納=>有効な探索方向には値が残り無効な探索方向には-1が残る
			//0:[-1,-1] 1:[-1, 0] 2:[-1, 1]
			//3:[ 0,-1]   [ r, l] 4:[ 0, 1]
			//5:[ 1,-1] 6:[ 1, 0] 7:[ 1, 1]
			Integer dir[][] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
			Integer ilist[] = {0,1,2,3,4,5,6,7};
			if(r == 0){
				ilist[0] = -1;
				ilist[1] = -1;
				ilist[2] = -1;
			}else if(r == 7){
				ilist[5] = -1;
				ilist[6] = -1;
				ilist[7] = -1;
			}
			if(l == 0){
				ilist[0] = -1;
				ilist[3] = -1;
				ilist[5] = -1;
			}else if(l == 7){
				ilist[2] = -1;
				ilist[4] = -1;
				ilist[7] = -1;
			}
			//System.out.println(Arrays.asList(ilist));

			for(int i: ilist){
				if(i >= 0){//i=-1の場合を除く
					//System.out.println(i);
					boolean turnFlag = false;//石をひっくり返すかどうか
					int tr,tl;//tr=行,tl=列
					for(tr=r+dir[i][0], tl=l+dir[i][1]; (0<=tr && tr<8 && 0<=tl && tl<8); tr+=dir[i][0], tl+=dir[i][1]){//行列が0～7の範囲を超えないように
						//System.out.println("tr:"+tr+" tl:"+tl);
						if(map[tr][tl] == target){
							//turnFlagがtrue->trueの場合は探索を続行
							//turnFlagがfalse->trueの場合は続行(初回のみ発生)
							turnFlag = true;
						}else if(turnFlag){
							//turnFlagがtrue->falseと変化し、かつmap[tr][tl]が自分の手番情報である場合は実際にひっくり返す必要が生じる
							//ひっくり返す
							if(map[tr][tl] == p){
								//System.out.println("turn");
								int ttr, ttl;
								for(ttr=r, ttl=l; (tr!=ttr || tl!=ttl); ttr+=dir[i][0], ttl+=dir[i][1]){
									map[ttr][ttl] = p;
								}
								break;
							}else{
								break;
							}
						}else{
							//turnFlagがfalse->falseの場合はこの探索方向での探索を終了(初回のみ発生)
							break;
						}
					}
					//探索終了時にturnFlagがtrueの場合はひっくり返せない
				}
			}
		}

		//checkMap(p);
		//printMap();
	}

	public void checkMap(int t){//t:自分の手番情報=0or1
		//「自分の」置ける場所を調べ、そのマスを2に変える

		//マスを絞るのが面倒なので全マスに対して探索
		int j,k;//j=行,k=列
		for(j=0; j<8; j++){
			for(k=0; k<8; k++){
				//map[j][k]の周囲8マスを探索
				if(map[j][k] == 0 || map[j][k] == 1){
					//map[j][k]に既に石が置かれていたら判別する必要がないのでcontinue
					continue;
				}else{
					//置ける場所(2)の情報が残っていると面倒なので一旦3でリセット
					map[j][k] = 3;
				}

				//1が置かれたら0を、0が置かれたら1を探索する
				int target = 1 - t;//探索対象

				//探索方向を指定してilistに格納=>有効な探索方向には値が残り無効な探索方向には-1が残る
				//0:[-1,-1] 1:[-1, 0] 2:[-1, 1]
				//3:[ 0,-1]   [ r, l] 4:[ 0, 1]
				//5:[ 1,-1] 6:[ 1, 0] 7:[ 1, 1]
				Integer dir[][] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
				Integer ilist[] = {0,1,2,3,4,5,6,7};
				if(j == 0){
					ilist[0] = -1;
					ilist[1] = -1;
					ilist[2] = -1;
				}else if(j == 7){
					ilist[5] = -1;
					ilist[6] = -1;
					ilist[7] = -1;
				}
				if(k == 0){
					ilist[0] = -1;
					ilist[3] = -1;
					ilist[5] = -1;
				}else if(k == 7){
					ilist[2] = -1;
					ilist[4] = -1;
					ilist[7] = -1;
				}
				//System.out.println(Arrays.asList(ilist));

				for(int i: ilist){
					if(i >= 0){//i=-1の場合を除く
						boolean turnFlag = false;//石をひっくり返せるかどうか
						int tr,tl;//tr=行,tl=列
						for(tr=j+dir[i][0], tl=k+dir[i][1]; (0<=tr && tr<8 && 0<=tl && tl<8); tr+=dir[i][0], tl+=dir[i][1]){//行列が0～7の範囲を超えないように
							if(map[tr][tl] == target){
								//turnFlagがtrue->trueの場合は探索を続行
								//turnFlagがfalse->trueの場合は続行(初回のみ発生)
								turnFlag = true;
							}else if(turnFlag){
								//turnFlagがtrue->falseと変化し、かつmap[tr][tl]が自分の手番情報である場合は実際にひっくり返せる
								if(map[tr][tl] == t){
									map[j][k] = 2;
									break;//1方向でもひっくり返せれば良いのでループを抜ける
								}else{
									break;
								}
							}else{
								//turnFlagがfalse->falseの場合はこの探索方向での探索を終了(初回のみ発生)
								break;
							}
						}
					//探索終了時にturnFlagがtrueの場合はひっくり返せない
					}
				}
			}
		}//j,kによる全探索終了
	}

	public int countNumber(int n){//n:数えたい数字(0～3)
		int i,j,k=0;
		for(i=0; i<8; i++){
			for(j=0; j<8; j++){
				if(map[i][j] == n){
					k++;
				}
			}
		}

		return k;
	}

	public int isGameFinish(){//ゲームが終了した:0or1or2[0=黒勝,1=白勝,2=引き分け],終了しない:3
		//両者置ける場所が無くなったときにゲーム終了
		int b,b2,w,w2;
		checkMap(0);
		b = countNumber(0);//黒石の個数
		b2 = countNumber(2);//黒の置ける場所の数
		//printMap();

		checkMap(1);
		w = countNumber(1);//白石の個数
		w2 = countNumber(2);//白の置ける場所の数
		//printMap();

		//checkMap(turn);//map[][]を元に戻しておく

		if(b2 == 0 && w2 == 0){
			//ゲーム終了確定
			if(b > w){
				return 0;
			}else if(w > b){
				return 1;
			}else{
				return 2;
			}
		}else{
			return 3;
		}
	}

}
