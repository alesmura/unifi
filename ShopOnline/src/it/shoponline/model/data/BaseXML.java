package it.shoponline.model.data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class BaseXML
{
	private final static String APPLICATION_FOLDER = "ShopOnline";
	private final static String GET = "get";
	private final static String SET = "set";
	private final static String ID = "id";

	private File xmlFile;
	private Document doc;

	public BaseXML()
	{
		try
		{
			this.xmlFile = retrieveOrCreateXMLFile(getNomeFileXML(), getRootTagName());
			this.doc = parseDocument();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	abstract String getNomeFileXML();

	abstract String getRootTagName();

	abstract String getElementTagName();

	private static File retrieveOrCreateXMLFile(String fileName, String rootTagName) throws Exception
	{
		String tmpDirStr = System.getProperty("java.io.tmpdir");
		if (tmpDirStr == null)
			throw new IOException("System property 'java.io.tmpdir' does not specify a tmp dir");

		File tmpDir = new File(tmpDirStr);
		if (!tmpDir.exists())
			if (!tmpDir.mkdirs())
				throw new IOException("Unable to create tmp dir " + tmpDir);

		// Directory dell'applicazione
		File resultDir = new File(tmpDir, APPLICATION_FOLDER);
		if (!resultDir.exists())
			if (!resultDir.mkdir())
				throw new IOException("Errore creazione directory dell'applicazione");
		// File
		if (!fileName.endsWith(".xml"))
			fileName = fileName.concat(".xml");

		File resultFile = new File(resultDir, fileName);
		if (!resultFile.exists())
		{
			if (!resultFile.createNewFile())
				throw new IOException("Errore creazione file xml");
			// Creo la radice dell'xml
			createRoot(resultFile, rootTagName);
		}
		return resultFile;
	}

	private static void createRoot(File xmlFile, String rootTagName) throws Exception
	{
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		// Creo un nuovo document
		Document document = docBuilder.newDocument();
		// Creo l'elemento di radice
		Element element = document.createElement(rootTagName);
		// Lo inserisco nel documento
		document.appendChild(element);
		// Scrivo il file
		writeFile(document, xmlFile);
	}

	private static void writeFile(Document doc, File xmlFile)
	{
		try
		{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
		}
		catch (TransformerException e)
		{
			e.printStackTrace();
		}
	}

	private Document parseDocument() throws Exception
	{
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
	}

	private Element getNewElement(String id)
	{
		Element element = doc.createElement(getElementTagName());
		// Attribuisco al nodo una chiave
		Attr attr = doc.createAttribute(ID);
		attr.setValue(id);
		element.setAttributeNode(attr);

		return element;
	}

	private Element getNewTag(String tagName, Object value)
	{
		if (tagName.startsWith(GET))
			tagName = tagName.substring(GET.length());
		Element element = doc.createElement(tagName);
		element.appendChild(doc.createTextNode(String.valueOf(value)));
		return element;
	}

	private Element getElementByKey(String key)
	{
		try
		{
			NodeList nList = doc.getElementsByTagName(getElementTagName());
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element el = (Element) nNode;
					if (key.equals(el.getAttribute(ID)))
						return el;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private Node getRoot() throws Exception
	{
		NodeList nList = doc.getElementsByTagName(getRootTagName());
		int l = nList.getLength();
		if (l == 0)
			return null;
		if (l > 1)
			throw new RuntimeException("Deve esistere una sola radice!");
		return nList.item(0);
	}

	private void appendNewElement(Object obj, String id) throws Exception
	{
		Element cliente = getNewElement(id);
		Method[] methods = obj.getClass().getMethods();
		for (Method m : methods)
		{
			String name = m.getName();
			// Elaboro solo i getter
			if (!name.startsWith(GET) || "getClass".equals(name))
				continue;
			cliente.appendChild(getNewTag(name, m.invoke(obj)));
		}

		// Appendo alla radice
		getRoot().appendChild(cliente);
	}

	//
	protected boolean isElementoPresente(String id)
	{
		return getElementByKey(id) != null;
	}

	protected <T> T getObjectFromElement(Class<T> clazz, String id)
	{
		Element element = getElementByKey(id);
		if (element == null)
			return null;
		//
		try
		{
			Object objRet = clazz.getConstructor().newInstance();
			NodeList nList = element.getChildNodes();
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element el = (Element) nNode;
					if (el.getChildNodes().getLength() != 1)
						continue;
					Node valNode = el.getChildNodes().item(0);
					if (valNode == null || valNode.getNodeType() != Node.TEXT_NODE)
						continue;
					org.w3c.dom.CharacterData val = (org.w3c.dom.CharacterData) valNode;
					Method m = clazz.getMethod(SET + el.getNodeName(), String.class);
					m.invoke(objRet, val.getNodeValue());
				}
			}
			return (clazz.cast(objRet));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	protected void storeElement(Object obj, String id)
	{
		try
		{
			if (isElementoPresente(id))
				return;
			// Aggiungo alla radice un nuovo cliente
			appendNewElement(obj, id);
			// Scrivo il file
			writeFile(doc, xmlFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}