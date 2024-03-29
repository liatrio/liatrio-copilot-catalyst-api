{{ if and .Values.blue.enabled .Values.green.enabled }}
---
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: {{ include "dks-api.fullname" . }}
spec:
  host: {{ .Chart.Name }}
  subsets:
    - name: prod
      labels:
        slot: {{ include "dks-api.productionSlot" . }}
    - name: test
      labels:
        slot: {{ include "dks-api.testSlot" . }}
{{ end }}
