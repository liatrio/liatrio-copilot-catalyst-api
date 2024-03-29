{{ if .Values.blue.enabled }}
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "dks-api.fullname" . }}-blue
  labels:
    {{- include "dks-api.labels" . | nindent 4 }}
    slot: blue
  {{- with .Values.deployment.blue.annotations }}
  annotations:
    {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- if not .Values.autoscaling.enabled }}
  replicas: {{ .Values.deployment.blue.replicaCount }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "dks-api.selectorLabels" . | nindent 6 }}
      slot: blue
  template:
    metadata:
      labels:
        {{- include "dks-api.labels" . | nindent 8 }}
        slot: blue
      annotations:
        {{- tpl ( .Values.deployment.pod.annotations | toYaml ) . | nindent 8 }}
    spec:
      imagePullSecrets:
        {{- toYaml .Values.deployment.blue.imagePullSecret | nindent 8 }}
      containers:
        - name: {{ .Chart.Name }}
          {{- if .Values.deployment.blue.startScript }}
          {{- tpl ( .Values.deployment.blue.startScript) . | nindent 10 }}
          {{- end }}
          {{$data := dict "depObj" .Values.deployment.blue "Release" $.Release "Chart" $.Chart }}
          image: {{ include "dks-api.container" $data }}
          imagePullPolicy: {{ .Values.deployment.blue.image.pullPolicy }}
          ports:
            {{- toYaml .Values.deployment.blue.ports | nindent 12 }}
          {{- if hasKey .Values.deployment.blue "livenessProbe" }}
          livenessProbe:
            {{- toYaml .Values.deployment.blue.livenessProbe | nindent 12}}
          {{- end }}
          {{- if hasKey .Values.deployment.blue "readinessProbe" }}
          readinessProbe:
            {{- toYaml .Values.deployment.blue.readinessProbe | nindent 12}}
          {{- end }}
          {{- if hasKey .Values.deployment.blue "env" }}
          env:
            {{- toYaml .Values.deployment.blue.env | nindent 12}}
          {{- end }}
          resources:
            {{- toYaml .Values.deployment.blue.resources | nindent 12 }}
{{ end }}
{{ if .Values.green.enabled }}
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "dks-api.fullname" . }}-green
  labels:
    {{- include "dks-api.labels" . | nindent 4 }}
    slot: green
  {{- with .Values.deployment.green.annotations }}
  annotations:
    {{- toYaml . | nindent 4 }}
  {{- end }}
spec:
  {{- if not .Values.autoscaling.enabled }}
  replicas: {{ .Values.deployment.green.replicaCount }}
  {{- end }}
  selector:
    matchLabels:
      {{- include "dks-api.selectorLabels" . | nindent 6 }}
      slot: green
  template:
    metadata:
      labels:
        {{- include "dks-api.labels" . | nindent 8 }}
        slot: green
      annotations:
        {{- tpl ( .Values.deployment.pod.annotations | toYaml ) . | nindent 8 }}
    spec:
      imagePullSecrets:
        {{- toYaml .Values.deployment.green.imagePullSecret | nindent 8 }}
      containers:
        - name: {{ .Chart.Name }}
          {{- if .Values.deployment.green.startScript }}
          {{- tpl ( .Values.deployment.green.startScript) . | nindent 10 }}
          {{- end }}
          {{$data := dict "depObj" .Values.deployment.green "Release" $.Release "Chart" $.Chart }}
          image: {{ include "dks-api.container" $data }}
          imagePullPolicy: {{ .Values.deployment.green.image.pullPolicy }}
          ports:
            {{- toYaml .Values.deployment.green.ports | nindent 12 }}
          {{- if hasKey .Values.deployment.green "livenessProbe" }}
          livenessProbe:
            {{- toYaml .Values.deployment.green.livenessProbe | nindent 12}}
          {{- end }}
          {{- if hasKey .Values.deployment.green "readinessProbe" }}
          readinessProbe:
            {{- toYaml .Values.deployment.green.readinessProbe | nindent 12}}
          {{- end }}
          {{- if hasKey .Values.deployment.green "env" }}
          env:
            {{- toYaml .Values.deployment.green.env | nindent 12}}
          {{- end }}
          resources:
            {{- toYaml .Values.deployment.green.resources | nindent 12 }}
{{ end }}
