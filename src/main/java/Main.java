import java.util.*;

import com.company.IncomingMessage;
import com.company.IncomingMessageList;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
// import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.util.ajax.JSON;
import org.whispersystems.websocket.messages.protobuf.ProtobufWebSocketMessageFactory;
import java.util.Optional;
// import java.io.ByteArrayOutputStream;
import java.io.ByteArrayOutputStream;



public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        // write your code here
        LinkedList<String> strings = new LinkedList<>();			//创建消息头表
        strings.add("Authorization: Kzg2MTUwNTEyMjAwMDAuMTo5OTk5OTk="); //发送方的usernumber.deviceID:password的base64格式，这里为+01234567890.1:123456
        strings.add("Unidentified-Access-Key: YWJjZA==");  //接收者的UAK，写死为abcd的base64
        strings.add("Content-Type: application/json");		//标记内容格式为json格式
        IncomingMessage message1 = new IncomingMessage(1, "+8615051223333", "aGVsbG8lMkN3b3JsZA==", 20211129L); //第一条消息，从左至右分别为type，destination，body（base64），timestamp。
        IncomingMessage message2 = new IncomingMessage(1, "+8615051223333", "YmFzZTY0", 20211130L);  //第二条消息
        List<IncomingMessage> messageList = new ArrayList<>();	//发送者发送的消息列表
        messageList.add(message1);
        // messageList.add(message2);
        IncomingMessageList incomingMessageList = new IncomingMessageList(messageList, true);

        byte[] message = new ProtobufWebSocketMessageFactory().createRequest(Optional.of(111L), "PUT", "/v1/messages/+8615051223333", strings,
                Optional.of(new ObjectMapper().writeValueAsBytes(incomingMessageList))).toByteArray(); //这里的+01234567891是接收者的Number

        // byte[] message = new ProtobufWebSocketMessageFactory().createRequest(Optional.of(111L), "PUT", "/v1/messages/+01234567891", strings, Optional.of(Json.toByteArray(incomingMessageList))).toByteArray(); //这里的+01234567891是接收者的Number
        //    String str = new String(message);
        byte[] encode = Base64.getEncoder().encode(message);	//HTTP请求的字节流格式（base64格式）
        System.out.println(new String(encode));					//输出
    }
}