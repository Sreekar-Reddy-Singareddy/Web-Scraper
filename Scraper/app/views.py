from django.shortcuts import render
from django.http import HttpResponse
from app.parse.parser import parse

# Create your views here.
def html_parser (request):
    result = parse()
    print(result)
    return HttpResponse(str(result))

