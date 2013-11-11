package controllers;

import play.libs.Json;
import play.mvc.*;

import crublib.CrudLib;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class API extends Controller {
	// # Create node
	// POST /api/nodes
	public static Result addNode() {
		JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out.println("[API] POST /api/nodes: " + json);

		if (json == null) {
			System.out.println("[API] Node not added: Expecting Json data");
			result.put("status", "ERR");
			result.put("message", "Node not added: Expecting Json data");
			result.put("type", "addNode");
			result.put("payload", "");
			return badRequest(result);
		} else {
			result_json = CrudLib.addNode(json);
			return ok(result_json);
		}
	}

	// # Retrieve node
	// GET /api/nodes/:uuid_name
	public static Result getNode(String uuid_name) {
		JsonNode result_json = Json.newObject();
		System.out.println("[API] GET /api/nodes/:uuid_name: " + uuid_name);

		result_json = CrudLib.getNode(uuid_name);
		return ok(result_json);
	}

	// # Nodes list
	// GET /api/nodes
	public static Result lstNodes() {
		JsonNode result_json = Json.newObject();
		System.out.println("[API] GET /api/nodes");

		result_json = CrudLib.lstNodes();
		return ok(result_json);
	}

	// # Search nodes
	// GET /api/nodes/search/:uuid_name
	public static Result srchNodes(String uuid_name) {
		JsonNode result_json = Json.newObject();
		System.out.println("[API] GET /api/nodes/search/:uuid_name: "
				+ uuid_name);

		result_json = CrudLib.srchNodes(uuid_name);
		return ok(result_json);
	}

	// # Update node
	// PUT /api/nodes/:uuid_namee
	public static Result updNode(String uuid_name) {
		JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out.println("[API] PUT /api/nodes/:uuid_name: " + uuid_name
				+ " com o conteudo: " + json);

		if (json == null) {
			System.out.println("[API] Node not added: Expecting Json data");
			result.put("status", "ERR");
			result.put("message", "Node not updated: Expecting Json data");
			result.put("type", "updNode");
			result.put("payload", "");
			return badRequest(result);
		} else {
			result_json = CrudLib.updNode(uuid_name, json);
			return ok(result_json);
		}
	}

	// # Delete node
	// DELETE /api/nodes/:uuid_name
	public static Result delNode(String uuid_name) {
		JsonNode result_json = Json.newObject();
		System.out.println("[API] DELETE /api/nodes/:uuid_name: " + uuid_name);

		result_json = CrudLib.delNode(uuid_name);
		return ok(result_json);
	}
}
