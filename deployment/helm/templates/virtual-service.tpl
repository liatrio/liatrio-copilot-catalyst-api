{{- if .Values.gateway.enabled }}
---
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: {{ include "dks-api.fullname" . }}
  labels:
    {{- include "dks-api.labels" . | nindent 4 }}
spec:
  hosts:
    - {{ .Values.fqdn }}
  gateways:
    - {{ .Chart.Name }}
  http:
    {{ if and .Values.blue.enabled .Values.green.enabled }}
    - name: test
      match:
        - headers:
            x-env:
              exact: "test"
      route:
        - destination:
            port:
              number: 80
            host: {{ .Chart.Name }}
            subset: test
    {{ end }}
    - name: prod
      route:
        - destination:
            port:
              number: 80
            host: {{ .Chart.Name }}
            {{ if and .Values.blue.enabled .Values.green.enabled }}
            subset: prod
            {{ end }}
{{- end }}
