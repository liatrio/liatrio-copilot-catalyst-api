{{/*
Expand the name of the chart.
*/}}
{{- define "dks-api.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "dks-api.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "dks-api.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "dks-api.labels" -}}
helm.sh/chart: {{ include "dks-api.chart" . }}
app: {{ include "dks-api.name" . }}
version: {{ default .Chart.AppVersion .Values.tag }}
{{ include "dks-api.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
{{- if .Values.gitTag }}
tag: {{ .Values.gitTag | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- include "dks-api.liatrioLabels" . }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "dks-api.selectorLabels" -}}
app.kubernetes.io/name: {{ include "dks-api.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
liatrio labels
*/}}
{{- define "dks-api.liatrioLabels" -}}
{{- if .Values.teamname }}
liatrio.com/managed-by-team: {{ .Values.teamname }}
{{- end -}}
{{- if .Values.reponame }}
liatrio.com/repo: {{ .Values.reponame }}
{{- end -}}
{{- end -}}

{{/*
Image Repository
*/}}
{{- define "dks-api.container" -}}
{{- if .depObj.image.registry }}
    {{- .depObj.image.registry -}}
    /
{{- end -}}
{{- .depObj.image.repository -}}
:
{{- .depObj.image.tag | default .Chart.AppVersion }}
{{- end -}}

{{ define "dks-api.productionSlot" -}}
{{- if eq .Values.productionSlot "blue" -}}
blue
{{- else -}}
green
{{- end -}}
{{- end }}

{{ define "dks-api.testSlot" -}}
{{- if eq .Values.productionSlot "blue" -}}
green
{{- else -}}
blue
{{- end -}}
{{- end }}
