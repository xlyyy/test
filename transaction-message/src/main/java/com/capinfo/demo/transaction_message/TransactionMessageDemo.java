package com.capinfo.demo.transaction_message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 示例可靠消息原型<br/>
 * 示例中1代表业务处理成功,0代表业务处理失败.
 * 
 * @author LuoAnDong
 *
 */
public class TransactionMessageDemo {

	// 可靠消息预存储接口
	static Map<String, MessageBean> tms = new HashMap<String, MessageBean>();

	// 1代表业务处理成功,0代表业务处理失败.
	static Random random = new Random();

	int randomNum = 10;
	static int MAX_SEND_TIME = 5; // 最大发送次数,超过则标记为死亡
	int defaultRandom = 2;

	class MessageBean {
		String mId; // 消息id/业务id
		int sendCount = 0; // 发送次数
		int status = 0; // 状态(1预发送|2发送中|3已消费|4死亡|5已删除)
	}

	/**
	 * 生产者处理消息并发送
	 */
	public String producer() {
		System.out.println(Thread.currentThread().getName());
		// 生产者预发送消息
		MessageBean m = new MessageBean();
		m.mId = System.currentTimeMillis() + "";

		//2,4,6,8,10失败
		if (random.nextInt(randomNum + defaultRandom) % defaultRandom == 0) {
			System.out.println("producer\t业务:" + m.mId + ",预发送失败,直接回滚本地事务.");
			return null;
		}
		//1,3,5,7,9,11预发送成功
		m.status = 1;
		tms.put(m.mId, m);
		System.out.println("producer\t业务:" + m.mId + ",预发送成功");
        
		int i = random.nextInt(randomNum + defaultRandom) % defaultRandom;
		if (i == 1) { // 业务处理成功
			// 发送消息
			m.status = 2;
			tms.put(m.mId, m);
			System.out.println("producer\t业务:" + m.mId + ",处理成功,消息发送消息");
		} else {
			System.out.println("producer\t业务:" + m.mId + ",处理异常,消息未发送");
			return null;
		}
		return m.mId;
	}

	/**
	 * 消费者消费消息并确认消息被消息
	 */
	private void consumer(final String mId) {

		new Thread(new Runnable() {

			public void run() {
				System.out.println(Thread.currentThread().getName());
				// 判断业务是否已经消费
				if (mId != null && random.nextInt(randomNum + defaultRandom) % defaultRandom == 1) {
					MessageBean m = tms.get(mId);
					System.out.println("consumer\t业务:" + m.mId + ",接收消息成功");

					// 幂等处理,具体结合业务(此处一直为true)
					boolean hasDoing = false;
					MessageBean om = tms.get(mId);
					if (om != null && om.status == 3) {//已消费
						hasDoing = true;
					}
					// 幂等处理结束

					if (!hasDoing) {
						// 删除消息
						m.status = 3;
						tms.put(mId, m);
						System.out.println("consumer\t业务:" + m.mId + ",成功消费.");
					} else {
						System.out.println("consumer\t业务:" + m.mId + ",幂等处理,消息已存在正常消费.");
					}

				} else {
					System.out.println("consumer\t业务:" + mId + ",处理异常,未能成功消费");
				}
			}
		}).start();
	}

	/**
	 * 消息监控
	 */
	public void monitor() {
		System.out.println(">>>>>>>");
		Iterator<String> iterator = tms.keySet().iterator();
		int messageCount = 0;
		int dealMessage = 0;
		while (iterator.hasNext()) {
			String key = iterator.next();
			MessageBean mb = tms.get(key);
			if (mb.status != 3 && mb.status != 5) {
				messageCount++;
				System.out.println("monitor \t业务 :" + key + "\t消息状态:" + mb.status + "\t发送次数:" + mb.sendCount);
			}
			if (mb.status == 4) {
				dealMessage++;
			}
		}
		System.out.println(
				"monitor \t总消息量:" + tms.keySet().size() + "\t未处理消息量:" + messageCount + "\t死亡消息:" + dealMessage);
		System.out.println(">>>>>>>");
	}

	/**
	 * 消息异常处理
	 */
	private void outtime() {
		new Thread(new Runnable() {

			public void run() {
				Iterator<String> iterator = tms.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					MessageBean mb = tms.get(key);

					// 未发出的消息
					if (mb.status == 1) {
						// 判断业务是否完成
						if (random.nextInt(randomNum + defaultRandom) % defaultRandom == 1) {
							System.out.println("outtime \t业务:" + key + ",业务已经完成重新发送.");
							// 重新发送
							mb.status = 2;
							consumer(key);
						} else {
							// 业务未完成
							System.out.println("outtime \t业务:" + key + ",业务未完成,删除消息.");
							// 删除消息
							mb.status = 5 ; 
							tms.put(key, mb) ; 
						}
					}

					// 未成功的消费的消息,重新发送
					if (mb.status == 2) {

						if (mb.sendCount >= MAX_SEND_TIME) {
							mb.status = 4; // 消息死亡
							System.out.println("outtime \t业务:" + key + ",消息死亡.");
						} else {
							mb.sendCount += 1;
							System.out.println("outtime \t业务:" + key + ",重新发送消息.");
						}
						tms.put(key, mb);
						consumer(key);
					}
				}
			}
		}).start();
	}

	public void doMessage() throws InterruptedException {

		while (true) {

			// 生产者
			String mId = producer();

			// 消费者
			if (mId != null) {
				// 模拟消息投递
				consumer(mId);
				// 模拟消息投递结束
			}

			// 消息超时
			outtime();
			
			// 消息监控
			monitor();

			System.out.println("--------------------------------------------------");
			
			Thread.sleep(1000);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TransactionMessageDemo d = new TransactionMessageDemo();
		d.doMessage();
	}

}
