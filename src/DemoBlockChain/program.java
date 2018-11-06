package DemoBlockChain;

import java.util.ArrayList;

public class program{

	public static ArrayList<Block> blockchain = new ArrayList<>();
	public static int difficulty = 5;
	
	public static void main(String[] args) {
		System.out.println("Dao block 1...");
		addBlock(new Block("Block thu 1","0"));
		System.out.println("Dao block 2...");
		addBlock(new Block("Block thu 2",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Dao block 3...");
		addBlock(new Block("Block thu 3",blockchain.get(blockchain.size()-1).hash));
	
		String blockchainJson = StringUtil.getJson(blockchain);
		System.out.println("The block chain: ");
		System.out.println(blockchainJson);
	}
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		for(int i=1; i<blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}