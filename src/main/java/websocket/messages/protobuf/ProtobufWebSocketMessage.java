/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.websocket.messages.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.whispersystems.websocket.messages.InvalidMessageException;
import org.whispersystems.websocket.messages.WebSocketMessage;
import org.whispersystems.websocket.messages.WebSocketRequestMessage;
import org.whispersystems.websocket.messages.WebSocketResponseMessage;
// import org.whispersystems.messages.protobuf.SubProtocol;

public class ProtobufWebSocketMessage implements WebSocketMessage {

  private final org.whispersystems.websocket.messages.protobuf.SubProtocol.WebSocketMessage message;

  ProtobufWebSocketMessage(byte[] buffer, int offset, int length) throws InvalidMessageException {
    try {
      this.message = org.whispersystems.websocket.messages.protobuf.SubProtocol.WebSocketMessage.parseFrom(ByteString.copyFrom(buffer, offset, length));

      if (getType() == Type.REQUEST_MESSAGE) {
        if (!message.getRequest().hasVerb() || !message.getRequest().hasPath()) {
          throw new InvalidMessageException("Missing required request attributes!");
        }
      } else if (getType() == Type.RESPONSE_MESSAGE) {
        if (!message.getResponse().hasId() || !message.getResponse().hasStatus() || !message.getResponse().hasMessage()) {
          throw new InvalidMessageException("Missing required response attributes!");
        }
      }
    } catch (InvalidProtocolBufferException e) {
      throw new InvalidMessageException(e);
    }
  }

  ProtobufWebSocketMessage(org.whispersystems.websocket.messages.protobuf.SubProtocol.WebSocketMessage message) {
    this.message = message;
  }

  @Override
  public Type getType() {
    if (message.getType().getNumber() == org.whispersystems.websocket.messages.protobuf.SubProtocol.WebSocketMessage.Type.REQUEST_VALUE &&
        message.hasRequest())
    {
      return Type.REQUEST_MESSAGE;
    } else if (message.getType().getNumber() == org.whispersystems.websocket.messages.protobuf.SubProtocol.WebSocketMessage.Type.RESPONSE_VALUE &&
               message.hasResponse())
    {
      return Type.RESPONSE_MESSAGE;
    } else {
      return Type.UNKNOWN_MESSAGE;
    }
  }

  @Override
  public WebSocketRequestMessage getRequestMessage() {
    return new ProtobufWebSocketRequestMessage(message.getRequest());
  }

  @Override
  public WebSocketResponseMessage getResponseMessage() {
    return new ProtobufWebSocketResponseMessage(message.getResponse());
  }

  @Override
  public byte[] toByteArray() {
    return message.toByteArray();
  }
}
