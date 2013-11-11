package crublib;

import java.util.List;

import play.libs.Json;

import models.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CrudLib {
	// # Create node
	public static JsonNode addNode(JsonNode json) {
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();

		result.put("type", "addNode");

		try {
			Node node = Json.fromJson(json, Node.class);
			System.out.println("[CrudLib] Salvando node: " + Json.toJson(node));
			node.save();
			result.put("status", "OK");
			result.put("message", "Node added");
			result.put("payload", Json.toJson(node));
		} catch (Exception e) {
			System.out.println("[CrudLib] ERR: " + e);
			result.put("status", "ERR");
			result.put("message", "Node not added");
			result.put("payload", e.toString());
		}

		result_json = Json.fromJson(result, JsonNode.class);
		return result_json;
	}

	// # Retrieve node
	public static JsonNode getNode(String uuid_name) {
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out.println("[CrudLib] Pesquisando node por uuid ou name: "
				+ uuid_name);

		result.put("type", "getNode");

		List<Node> uuidlist = Node.find.where().ieq("uuid", uuid_name)
				.findList();
		List<Node> namelist = Node.find.where().ieq("name", uuid_name)
				.findList();

		if (!uuidlist.isEmpty()) {
			System.out.println("[CrudLib] Encontrado node por uuid: "
					+ uuid_name);
			result.put("status", "OK");
			result.put("message", "Node found by uuid: " + uuid_name);
			result.put("payload", Json.toJson(uuidlist));
		} else if (!namelist.isEmpty()) {
			System.out.println("[CrudLib] Encontrado node por name: "
					+ uuid_name);
			result.put("status", "OK");
			result.put("message", "Node found by name: " + uuid_name);
			result.put("payload", Json.toJson(namelist));
		} else {
			System.out.println("[CrudLib] Não foi encontrado node: "
					+ uuid_name);
			result.put("status", "ERR");
			result.put("message", "Node not found");
			result.put("payload", uuid_name);
		}

		result_json = Json.fromJson(result, JsonNode.class);
		return result_json;
	}

	// # Nodes list
	public static JsonNode lstNodes() {
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out.println("[CrudLib] Listando todos os nodes");

		result.put("status", "OK");
		result.put("message", "Node list");
		result.put("type", "lstNodes");
		result.put("payload", Json.toJson(Node.find.all()));

		result_json = Json.fromJson(result, JsonNode.class);
		return result_json;
	}

	// # Search nodes
	public static JsonNode srchNodes(String uuid_name) {
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out
				.println("[CrudLib] Pesquisando node por uuid ou name contendo: "
						+ uuid_name);

		result.put("type", "srchNodes");

		List<Node> uuidlist = Node.find.where()
				.ilike("uuid", "%" + uuid_name + "%").findList();
		List<Node> namelist = Node.find.where()
				.ilike("name", "%" + uuid_name + "%").findList();

		if (!uuidlist.isEmpty()) {
			System.out.println("[CrudLib] Encontrado node por uuid contendo: "
					+ uuid_name);
			result.put("status", "OK");
			result.put("message", "Nodes found by uuid substring: " + uuid_name);
			result.put("payload", Json.toJson(uuidlist));
		} else if (!namelist.isEmpty()) {
			System.out.println("[CrudLib] Encontrado node por name contendo: "
					+ uuid_name);
			result.put("status", "OK");
			result.put("message", "Nodes found by name substring: " + uuid_name);
			result.put("payload", Json.toJson(namelist));
		} else {
			System.out
					.println("[CrudLib] Não foi encontrado node contendo substring: "
							+ uuid_name);
			result.put("status", "ERR");
			result.put("message", "No node found");
			result.put("payload", "Substring search: " + uuid_name);
		}

		result_json = Json.fromJson(result, JsonNode.class);
		return result_json;
	}

	// # Update node
	public static JsonNode updNode(String uuid_name, JsonNode json) {
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out.println("[CrudLib] Atualizando node: " + uuid_name
				+ " com o conteudo: " + json);

		result.put("type", "updNode");

		try {
			Node uuidnode = Node.find.where().ieq("uuid", uuid_name)
					.findUnique();
			if (uuidnode != null) {
				Node node = Json.fromJson(json, Node.class);
				uuidnode.uuid = node.uuid;
				uuidnode.name = node.name;
				uuidnode.info = node.info;
				System.out.println("[CrudLib] Atualizando node por uuid \""
						+ uuid_name + "\" com conteúdo: " + Json.toJson(node));
				uuidnode.save();
				result.put("status", "OK");
				result.put("message", "Node updated by uuid: " + uuid_name);
				result.put("payload", Json.toJson(node));
				result_json = Json.fromJson(result, JsonNode.class);
				return result_json;
			}
		} catch (Exception e) {
			System.out.println("[CrudLib] ERR: " + e);
		}

		try {
			Node namenode = Node.find.where().ieq("name", uuid_name)
					.findUnique();
			if (namenode != null) {
				Node node = Json.fromJson(json, Node.class);
				namenode.uuid = node.uuid;
				namenode.name = node.name;
				namenode.info = node.info;
				System.out.println("[CrudLib] Atualizando node por name \""
						+ uuid_name + "\" com conteúdo: " + Json.toJson(node));
				namenode.save();
				result.put("status", "OK");
				result.put("message", "Node updated by name: " + uuid_name);
				result.put("payload", Json.toJson(node));
				result_json = Json.fromJson(result, JsonNode.class);
				return result_json;
			}
		} catch (Exception e) {
			System.out.println("[CrudLib] ERR: " + e);
		}

		System.out.println("[CrudLib] Não foi encontrado node: " + uuid_name);
		result.put("status", "ERR");
		result.put("message", "Node not found");
		result.put("payload", uuid_name);

		result_json = Json.fromJson(result, JsonNode.class);
		return result_json;
	}

	// # Delete node
	public static JsonNode delNode(String uuid_name) {
		ObjectNode result = Json.newObject();
		JsonNode result_json = Json.newObject();
		System.out.println("[CrudLib] Deletando node por uuid ou name: "
				+ uuid_name);

		result.put("type", "delNode");

		try {
			Node uuidnode = Node.find.where().ieq("uuid", uuid_name)
					.findUnique();
			if (uuidnode != null) {
				System.out.println("[CrudLib] Deletando node por uuid: "
						+ uuid_name);
				uuidnode.delete();
				result.put("status", "OK");
				result.put("message", "Node deleted by uuid");
				result.put("payload", uuid_name);
				result_json = Json.fromJson(result, JsonNode.class);
				return result_json;
			}
		} catch (Exception e) {
			System.out.println("[CrudLib] ERR: " + e);
		}

		try {
			Node namenode = Node.find.where().ieq("name", uuid_name)
					.findUnique();
			if (namenode != null) {
				System.out.println("[CrudLib] Deletando node por name: "
						+ uuid_name);
				namenode.delete();
				result.put("status", "OK");
				result.put("message", "Node deleted by name");
				result.put("payload", uuid_name);
				result_json = Json.fromJson(result, JsonNode.class);
				return result_json;
			}
		} catch (Exception e) {
			System.out.println("[CrudLib] ERR: " + e);
		}

		System.out.println("[CrudLib] Não foi encontrado node: " + uuid_name);
		result.put("status", "ERR");
		result.put("message", "Node not found");
		result.put("payload", uuid_name);
		result_json = Json.fromJson(result, JsonNode.class);
		return result_json;
	}
}
