package com.glauber.nfeuploadservice.infrastructure.service.xml;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glauber.nfeuploadservice.domain.model.NotaFiscal;
import com.glauber.nfeuploadservice.domain.service.NotaFiscalXmlParseToDomainObjectService;

@Service
public class SAXBuilderXmlParseToDomainObjectServiceImpl implements NotaFiscalXmlParseToDomainObjectService {

	@Autowired
	private SAXBuilder saxBuilder;

	@Override
	public NotaFiscal parce(String xml) {	
		try {
			NotaFiscal notaFiscal = getNotaFiscalFromXml(xml);
			return notaFiscal;
			
		} catch (JDOMException e) {
			//TODO: tratar as exceptions
			throw new RuntimeException(e);
		} catch (IOException e) {
			//TODO: tratar as exceptions
			throw new RuntimeException(e);
		}
	}

	private NotaFiscal getNotaFiscalFromXml(String xml) throws JDOMException, IOException {
		NotaFiscal notaFiscal = new NotaFiscal();
		
		Document document = saxBuilder.build(new StringReader(xml));
		
		Element root = document.getRootElement();
		Element nfe = root.getChildren().get(0);
		Element infNFe = nfe.getChildren().get(0);
		
		infNFe.getChildren().stream().forEach(element -> {
			setNumeroAndDhRegistroOfIdeElement(notaFiscal, element);
			setNomeEmitenteOfEmitElement(notaFiscal, element);
			setNomeDestinatarioOfDestElement(notaFiscal, element);
			setValorNotaOfPagElement(notaFiscal, element);
		});
		
		return notaFiscal;
	}

	private void setValorNotaOfPagElement(NotaFiscal notaFiscal, Element element) {
		if ("pag".equals(element.getName())) {
			element.getChildren().stream().forEach(pagElement -> {
				if ("detPag".equals(pagElement.getName())) {
					pagElement.getChildren().stream().forEach(vPagElement -> {
						if ("vPag".equals(vPagElement.getName())) {
							notaFiscal.setValorNota(new BigDecimal(vPagElement.getValue()));
						}
					});
				}
			});
		}
	}

	private void setNomeDestinatarioOfDestElement(NotaFiscal notaFiscal, Element element) {
		if ("dest".equals(element.getName())) {
			element.getChildren().stream().forEach(destElement -> {
				if ("xNome".equals(destElement.getName())) {
					notaFiscal.setNomeDestinatario(destElement.getValue());
				}
			});
		}
	}

	private void setNomeEmitenteOfEmitElement(NotaFiscal notaFiscal, Element element) {
		if ("emit".equals(element.getName())) {
			element.getChildren().stream().forEach(emitElement -> {
				if ("xNome".equals(emitElement.getName())) {
					notaFiscal.setNomeEmitente(emitElement.getValue());
				}
			});
		}
	}

	private void setNumeroAndDhRegistroOfIdeElement(NotaFiscal notaFiscal, Element element) {
		if ("ide".equals(element.getName())) {
			element.getChildren().stream().forEach(ideElement -> {
				if ("nNF".equals(ideElement.getName())) {
					notaFiscal.setNumero(Integer.parseInt(ideElement.getValue()));
				}
				
				if ("dhEmi".equals(ideElement.getName())) {
					OffsetDateTime osdt = OffsetDateTime.parse(ideElement.getValue());

					notaFiscal.setDhRegistro(osdt.toLocalDateTime());
				}
			});
		}
	}

}
