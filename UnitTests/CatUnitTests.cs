using Application;
using Xunit;

namespace UnitTests
{
	public class CatUnitTests
    {
		[Fact]
		public void Talk_Test()
		{
			var sut = new Cat();
			var actual = sut.Talk();
			Assert.Equal("Meow!", actual);
		}
	}
}
