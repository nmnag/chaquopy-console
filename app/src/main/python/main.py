from six.moves import input
import requests
import pdfplumber


def main(string):
    print("Enter your name, or an empty line to exit.")
    with pdfplumber.load(string) as pdf:
        first_page = pdf.pages[0]
        text = first_page.extract_text()
        print(text)
    # while True:
    #     try:
    #         name = input()
    #     except EOFError:
    #         break
    #     if not name:
    #         break
    #     print("Hello {}!".format(name))
