from django.shortcuts import render
from django.http import HttpResponse
from app.parse.parser import parse

# Create your views here.
def html_parser (request):
    result = parse()
    return HttpResponse(str(result))

