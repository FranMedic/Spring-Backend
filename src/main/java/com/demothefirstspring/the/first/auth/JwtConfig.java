package com.demothefirstspring.the.first.auth;

public class JwtConfig {
	
	public static final String SECRET_KEY="zecreto";
	
	/*public static final String RSA_PRIVADA="-----BEGIN PRIVATE KEY-----\r\n"
			+ "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkTRKrZCjYxxBy\r\n"
			+ "Ew1ojIQXXktemdsscRLsfiYrcqzyOKotTgfCnC6RU93rbq56obqvwxMTIg5tD0Fi\r\n"
			+ "gEbWpi/qRI93LV6/Fr12ADINNxAXosT2Kx22pd0JN/9uD07E6sbrcQM/FW3Fv1oJ\r\n"
			+ "8S27THjdEJyAsZefb4J7nzI/i6cdwM/X2qfXHbm2OGpq0RGdayPckfEYbLQdnqCq\r\n"
			+ "Okua6P67Yml3oDkNmiBb/9M9l43C3XNzdxH0cRzx/UUmwpgulMivvozoREEaxs+e\r\n"
			+ "CT4+Kzsaw2HdERlieNnrAa+aMYMlh7uCrzg1wte/4oMQHaLbzUXXspNUH6J2SNm7\r\n"
			+ "fuTeHPobAgMBAAECggEAba+1+5B4q4YgmZ4bwBJa2FtefMoQ6m49OFB3iEJlY07k\r\n"
			+ "PDYUNmURXpNbuOSJgbO4XP+tZ3DiW3WL5YZaAoD/3l8oSosKzWZgipQXeA/vdFuo\r\n"
			+ "o9kdQoa4BtjXqQpDvwpJng7DD4j9H5gK62HrpeRc4WE8v/wTK+/IUhqvaqZvGTSE\r\n"
			+ "kW60Iccr3RR3NA2z+h7sUCUWCtrnNkR7nsYLQ1rWpaOFFNMjSc+S8RGpcCSGW4Cj\r\n"
			+ "+YDfSnxog09qAUqdRWmMFVdcGhlm6cta7D7tS08kmhC8a2Gxfnq1zV2RadFvHSZx\r\n"
			+ "obg9Oz2w4HJAFF0n4DYe73aw3RL4UzdX3w2xAKuYMQKBgQDtvsJVqmOcxIK4moDx\r\n"
			+ "8QJXQ+bCOl1z/mNjCADDppq8ZKbvBiiJRO20+U6550qEzTUbL2TFMUADjTuywqHx\r\n"
			+ "aAMNrqce4SiGB3WWCIr+s25MQ8odT5vf/HZoDoG+emodPyl3BfXNhVBiU1VPZbYL\r\n"
			+ "KGy6puEixC11j/2/mS8iKTYSaQKBgQD11K0Nhr5gU45CmDbjDAXhyJHEH+pvjZIb\r\n"
			+ "MQVG7sjMsnDzIqRg0ot8SIPsNQo9fU/0u62bdhP1xt+o37hNV3pPosrQYoenHSce\r\n"
			+ "xfnAdntWjomqs4HJXn7WMGj1j2bNUjiFJ5OHnwhVGMBJ4YCGwQtUFBi/jOosAPSY\r\n"
			+ "NT4Xx5KP4wKBgQDEI8DIVA5iY21FR5JiCGfZ/tb+HpDDBXiRVyTqRSUbkB54zCfd\r\n"
			+ "N9S0nYnprXu5HGNXzj3gwcUCKR67V/+q3hjGzPRrcW6xRK4p3VpuzzGMEdFJZUg/\r\n"
			+ "/lThfDZmIzAweM+Ito4XxIiQ1hdnrY4Gno81XkAXIqoud2DtFT/hewZ1IQKBgDWN\r\n"
			+ "qElrEiwf57zgNgzOQLXIIOEt5r94gtzDmBe0yy+0q+B7qn2hQFvJ3FtnzpxIoVN6\r\n"
			+ "Uz6rwXtoYK8EVb0bknyoxTASVuTz9+K/VEI2TXY0xQ7X3i1FFbKJh0P/gnC2ObPg\r\n"
			+ "REZmQcVwB+vQYVR514Lwgdew1g7SAnbqGmXJx0brAoGBAJ89KkS44ANqhJ+2xyvQ\r\n"
			+ "YJK8LRSFy+Net90pCbL3uofYxrW4LoVVtzC5tUl4197nmLR26Ckj8yI3jkdjzPeg\r\n"
			+ "3Zm+/8au+OvzEpkPxK8kObX89dj0Uz9ALfaEj67o5X1HH9kKxxJp+pJEa7rFd6dO\r\n"
			+ "xFv/+fq+RN/M3AlD32HFUNQ5\r\n"
			+ "-----END PRIVATE KEY-----";
			
	public static final String RSA_PUBLICA="-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5E0Sq2Qo2McQchMNaIyE\r\n"
			+ "F15LXpnbLHES7H4mK3Ks8jiqLU4HwpwukVPd626ueqG6r8MTEyIObQ9BYoBG1qYv\r\n"
			+ "6kSPdy1evxa9dgAyDTcQF6LE9isdtqXdCTf/bg9OxOrG63EDPxVtxb9aCfEtu0x4\r\n"
			+ "3RCcgLGXn2+Ce58yP4unHcDP19qn1x25tjhqatERnWsj3JHxGGy0HZ6gqjpLmuj+\r\n"
			+ "u2Jpd6A5DZogW//TPZeNwt1zc3cR9HEc8f1FJsKYLpTIr76M6ERBGsbPngk+Pis7\r\n"
			+ "GsNh3REZYnjZ6wGvmjGDJYe7gq84NcLXv+KDEB2i281F17KTVB+idkjZu37k3hz6\r\n"
			+ "GwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";*/
}
