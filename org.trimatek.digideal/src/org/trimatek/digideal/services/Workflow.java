package org.trimatek.digideal.services;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.states.New;
import org.trimatek.digideal.states.State;

public class Workflow {

	public static void main(String[] args) {

		try {

			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setRequiredSignatures(2);

			State newState = new New(cnt);
			newState.run();
			System.out.println("MultisigAddress: " + newState.getContract().getMultisigAddress());
			System.out.println("RedeemScript: " + newState.getContract().getRedeemScript());

//			cnt.setMultisigAddress("2MxQny9iFiLzkmAHY1DaLG49jxhXJEgHBam");
//			cnt.setRedeemScript(
//					"522103855f89bd110748ecd9aa7db621e4e0cf2706149cd4c56e96d5d0bf42ef20ffa82102f39056bb71d9a4a492938eb7fd1e667efba3db9d761b62c6cbd19a6c668cc235210314977363f96e1d7fed75205f22d933d5cf38997e19fc50ba65115ef00a91e68f53ae");
			
			cnt = newState.getContract();
			Repository.getInstance().save(cnt);
			
			System.out.println("Contract ID: " + cnt.getId());
			
//			Contract c = Repository.getInstance().loadContract(8);
//			System.out.println(c.getMultisigAddress());
			
			/*
			 * cnt.setUnspentTxId(
			 * "375a11b8cbe9118d000154146b632149b987060b9a61e90e1dbd9a5791feee53");
			 * 
			 * State req = new Requested(cnt); req.run(); System.out.println("Sts= " +
			 * req.getContract().getSts()); System.out.println("UnspentOutputScript= " +
			 * req.getContract().getUnspentOutputScript());
			 * System.out.println("UnspentVout= " + req.getContract().getUnspentVout());
			 * System.out.println("SpentTxRaw= " + req.getContract().getpayTx().getRaw());
			 * 
			 * cnt.setSts(new BigDecimal(1)); cnt.setUnspentVout(0);
			 * cnt.setUnspentOutputScript("a91438a7e73d7d714e62e485b411b1d207291d02c69487");
			 * cnt.pushPayTx(new Transaction("","SINFIRMAR")); cnt.pushPayTx(new
			 * Transaction("","PARTIALLYSIGNED")); cnt.pushPayTx(new Transaction("",
			 * "020000000153eefe91579abd1d0ee9619a0b0687b94921636b145401008d11e9cbb8115a3700000000fdfe0000483045022100c83fd7a5ad763c3c1950a9f87a02a9101c6b300e1c3d5e130a234e1354d6e7a7022041c34fd6f1f3732b05e346d262171bfd6aacf992606e364eda951e266ecc884501483045022100b324fc881520b86f057298da7541e08d66782927d3f001a36e9ce1cc9dcf980b02205cca7c9a473febfa0c681542ecd4f527f10ea5e1c748da7fab59aea9e2f5bba3014c69522103855f89bd110748ecd9aa7db621e4e0cf2706149cd4c56e96d5d0bf42ef20ffa82102f39056bb71d9a4a492938eb7fd1e667efba3db9d761b62c6cbd19a6c668cc235210314977363f96e1d7fed75205f22d933d5cf38997e19fc50ba65115ef00a91e68f53aeffffffff02c044f505000000001976a914b79919b17b61770c937b79d9bb15c6a753ae580d88ac204e0000000000001976a914358dc3f2926456e7d2e58a2a4a20c4ddac20789e88ac00000000"
			 * )); State received = new Received(cnt); received.run();
			 * System.out.println("SignedPayTxRaw" +
			 * received.getContract().getpayTx().getRaw());
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * New -> Sent -> Signed
	 * 
	 * 
	 */

}
