package start;

import io.aeron.Aeron;
import io.aeron.Publication;
import io.aeron.Subscription;
import io.aeron.driver.MediaDriver;
import io.aeron.logbuffer.FragmentHandler;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class App {

    public static void main(String[] args) {
        final String channel = "aeron:ipc";
        final String message = "my message";
        final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(ByteBuffer.allocateDirect(256));
        MediaDriver.launch();
        Aeron aeron = Aeron.connect();
        Subscription sub = aeron.addSubscription(channel, 10);
        Publication pub = aeron.addPublication(channel, 10);
        unsafeBuffer.putStringAscii(0, message);
        pub.offer(unsafeBuffer);
        final FragmentHandler handler = (buffer, offset, length, header) -> System.out
                .println("received: " + buffer.getStringAscii(offset));
        sub.poll(handler, 1);

    }
}
