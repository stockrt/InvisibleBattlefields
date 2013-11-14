package modellibrary;

import java.io.Serializable;
import java.util.UUID;

public class RequestInfo implements Serializable {
	/**
	 * Default Java serial version UID
	 **/
	private static final long serialVersionUID = 1L;

	/** Client UUID */
	private UUID _uuid;
	private String _type;
	private String _payload;

	/**
	 * Constructor.
	 **/
	public RequestInfo(UUID id, String type, String payload) {
		_uuid = id;
		_type = type;
		_payload = payload;
	}

	public UUID getUuid() {
		return _uuid;
	}

	public String getType() {
		return _type;
	}

	public String getPayload() {
		return _payload;
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public String toString() {
		return "Request";
	}
}
